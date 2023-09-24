package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.model.Aluguel;

import java.time.LocalDateTime;

public interface AluguelServicePort {

    Aluguel salvarAluguel(AluguelInput aluguelInput);

    Aluguel finalizarAluguel(Long id, LocalDateTime dataHoraDevolucaoEfetivada);
}
