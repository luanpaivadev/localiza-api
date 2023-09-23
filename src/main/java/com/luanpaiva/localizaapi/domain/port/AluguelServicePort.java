package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.model.Aluguel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AluguelServicePort {

    Aluguel salvarAluguel(AluguelInput aluguelInput);

    BigDecimal calcularCustoAluguel(LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao,
                                    BigDecimal valorDiaria);
}
