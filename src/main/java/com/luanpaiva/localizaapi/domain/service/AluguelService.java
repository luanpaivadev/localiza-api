package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.exception.AluguelNaoEncontradoException;
import com.luanpaiva.localizaapi.domain.exception.VeiculoNaoDisponivelException;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.port.AluguelRepositoryPort;
import com.luanpaiva.localizaapi.domain.port.AluguelServicePort;
import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import com.luanpaiva.localizaapi.domain.port.VeiculoServicePort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

import static com.luanpaiva.localizaapi.domain.model.StatusAluguel.ABERTO;
import static com.luanpaiva.localizaapi.domain.model.StatusAluguel.FECHADO;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.valueOf;
import static java.text.MessageFormat.format;
import static java.time.Duration.between;

public class AluguelService implements AluguelServicePort {

    private static final Boolean NAO = FALSE;
    private static final Boolean SIM = TRUE;
    private final AluguelRepositoryPort aluguelRepositoryPort;
    private final ClienteServicePort clienteServicePort;
    private final VeiculoServicePort veiculoServicePort;

    public AluguelService(AluguelRepositoryPort aluguelRepositoryPort, ClienteServicePort clienteServicePort, VeiculoServicePort veiculoServicePort) {
        this.aluguelRepositoryPort = aluguelRepositoryPort;
        this.clienteServicePort = clienteServicePort;
        this.veiculoServicePort = veiculoServicePort;
    }

    @Override
    public Aluguel salvarAluguel(AluguelInput aluguelInput) {

        String cpf = aluguelInput.getCpfCliente();
        Cliente cliente = clienteServicePort.buscarClientePeloCpf(cpf);

        String placaVeiculo = aluguelInput.getPlacaVeiculo();
        Veiculo veiculo = veiculoServicePort.buscarVeiculoPelaPlaca(placaVeiculo);

        if (veiculo.getDisponivel().equals(NAO)) {
            throw new VeiculoNaoDisponivelException(format("O veículo com placa {0}, não está disponível para aluguel no momento", placaVeiculo));
        }

        veiculo.setDisponivel(NAO);
        LocalDateTime dataHoraRetirada = aluguelInput.getDataHoraRetirada();
        LocalDateTime dataHoraDevolucaoPrevista = aluguelInput.getDataHoraDevolucao();

        BigDecimal valorPrevisto = calcularCustoAluguel(dataHoraRetirada, dataHoraDevolucaoPrevista, veiculo.getValorDiariaAluguel());

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDataHoraRetirada(dataHoraRetirada);
        aluguel.setDataHoraDevolucaoPrevista(dataHoraDevolucaoPrevista);
        aluguel.setValorPrevisto(valorPrevisto);
        aluguel.setValorExcedente(BigDecimal.ZERO);
        aluguel.setStatusAluguel(ABERTO);

        return aluguelRepositoryPort.save(aluguel);
    }

    @Override
    public Aluguel finalizarAluguel(Long id, LocalDateTime dataHoraDevolucaoEfetivada) {

        Aluguel aluguel = aluguelRepositoryPort.findById(id)
                .orElseThrow(() -> new AluguelNaoEncontradoException(format("Contrato de aluguel com id {0} não localizado.", id)));

        aluguel.setDataHoraDevolucaoEfetivada(dataHoraDevolucaoEfetivada);
        BigDecimal valorExcedente = calcularValorExcedente(aluguel, dataHoraDevolucaoEfetivada);
        aluguel.setValorExcedente(valorExcedente);
        aluguel.setValorTotal(aluguel.getValorPrevisto().add(aluguel.getValorExcedente()));
        aluguel.getVeiculo().setDisponivel(SIM);
        aluguel.setStatusAluguel(FECHADO);

        return aluguelRepositoryPort.save(aluguel);
    }

    private BigDecimal calcularCustoAluguel(LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao,
                                            BigDecimal valorDiaria) {

        Duration duracaoAluguel = between(dataHoraRetirada, dataHoraDevolucao);
        BigDecimal duracaoHoras = valueOf((double) duracaoAluguel.toMinutes() / 60);
        BigDecimal custoPorHora = valueOf(valorDiaria.doubleValue() / 24);

        return duracaoHoras.multiply(custoPorHora).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularValorExcedente(Aluguel aluguel, LocalDateTime dataHoraDevolucaoEfetivada) {

        LocalDateTime dataHoraDevolucaoPrevista = aluguel.getDataHoraDevolucaoPrevista();
        BigDecimal valorDiaria = aluguel.getVeiculo().getValorDiariaAluguel();
        BigDecimal custoPorHora = valueOf(valorDiaria.doubleValue() / 24);

        Duration duration = between(dataHoraDevolucaoPrevista, dataHoraDevolucaoEfetivada);
        BigDecimal horaExcedente = valueOf((double) duration.toMinutes() / 60);
        if (horaExcedente.compareTo(BigDecimal.valueOf(1)) <= 0) {
            return BigDecimal.ZERO;
        }

        return horaExcedente.multiply(custoPorHora).setScale(2, RoundingMode.HALF_UP);
    }
}
