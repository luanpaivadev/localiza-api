package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.ReservaInput;
import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaServicePort {

    List<Reserva> listarReservas(StatusReserva statusReserva);
    Reserva salvarReserva(ReservaInput reservaInput);
    Reserva finalizarReserva(Long id, LocalDateTime dataHoraDevolucaoEfetivada);
}
