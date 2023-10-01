package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepositoryPort {

    List<Reserva> findByStatus(StatusReserva statusReserva);
    Reserva save(Reserva reserva);
    Optional<Reserva> findById(Long id);
}
