package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.exception.VeiculoNaoDisponivelException;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.repository.AluguelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

import static com.luanpaiva.localizaapi.domain.model.StatusAluguel.ABERTO;
import static java.lang.Boolean.FALSE;
import static java.math.BigDecimal.valueOf;
import static java.text.MessageFormat.format;
import static java.time.Duration.between;

@Service
@AllArgsConstructor
public class AluguelService {

    public static final Boolean NAO = FALSE;
    private final AluguelRepository aluguelRepository;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;

    @Transactional
    public Aluguel salvarAluguel(AluguelInput aluguelInput) {

        String cpf = aluguelInput.getCpfCliente();
        Cliente cliente = clienteService.buscarClientePeloCpf(cpf);

        String placaVeiculo = aluguelInput.getPlacaVeiculo();
        Veiculo veiculo = veiculoService.buscarVeiculoPelaPlaca(placaVeiculo);

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

        return aluguelRepository.save(aluguel);
    }

    public BigDecimal calcularCustoAluguel(LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao,
                                                  BigDecimal valorDiaria) {

        Duration duracaoAluguel = between(dataHoraRetirada, dataHoraDevolucao);
        BigDecimal custoPorHora = valueOf(valorDiaria.doubleValue() / 24);
        BigDecimal duracaoHoras = valueOf((double) duracaoAluguel.toMinutes() / 60);

        return duracaoHoras.multiply(custoPorHora).setScale(2, RoundingMode.HALF_UP);
    }
}
