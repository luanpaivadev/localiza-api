package com.luanpaiva.localizaapi.adapter.output.repository;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.ReservaRepositoryJpa;
import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.ReservaEntity;
import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;
import com.luanpaiva.localizaapi.domain.port.ReservaRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ReservaRepositoryJpaAdapter implements ReservaRepositoryPort {

    private final ReservaRepositoryJpa reservaRepositoryJpa;

    @Override
    public List<Reserva> findByStatus(StatusReserva statusReserva) {
        return reservaRepositoryJpa.findByStatusReserva(statusReserva)
                .stream().map(ReservaEntity::toReserva)
                .toList();
    }

    @Override
    @Transactional
    public Reserva save(Reserva reserva) {
        ReservaEntity reservaEntity = ReservaEntity.toReservaEntity(reserva);
        reservaRepositoryJpa.save(reservaEntity);
        return reservaEntity.toReserva();
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        Optional<ReservaEntity> reservaEntityOptional = reservaRepositoryJpa.findById(id);
        return reservaEntityOptional.map(ReservaEntity::toReserva);
    }
}
