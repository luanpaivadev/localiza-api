package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
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
import static java.lang.Boolean.FALSE;
import static java.math.BigDecimal.valueOf;
import static java.text.MessageFormat.format;
import static java.time.Duration.between;

public class AluguelService implements AluguelServicePort {

    private static final Boolean NAO = FALSE;
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
        LocalDateTime dataHoraDevolucao = aluguelInput.getDataHoraDevolucao();

        BigDecimal custoAluguel = calcularCustoAluguel(dataHoraRetirada, dataHoraDevolucao, veiculo.getValorDiariaAluguel());

        Aluguel aluguel = Aluguel.builder()
                .cliente(cliente)
                .veiculo(veiculo)
                .dataHoraRetirada(dataHoraRetirada)
                .dataHoraDevolucao(dataHoraDevolucao)
                .valor(custoAluguel)
                .statusAluguel(ABERTO)
                .build();

        return aluguelRepositoryPort.save(aluguel);
    }

    @Override
    public BigDecimal calcularCustoAluguel(LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao,
                                           BigDecimal valorDiaria) {

        Duration duracaoAluguel = between(dataHoraRetirada, dataHoraDevolucao);
        BigDecimal duracaoHoras = valueOf((double) duracaoAluguel.toMinutes() / 60);
        BigDecimal custoPorHora = valueOf(valorDiaria.doubleValue() / 24);

        return duracaoHoras.multiply(custoPorHora).setScale(2, RoundingMode.HALF_UP);
    }
}
