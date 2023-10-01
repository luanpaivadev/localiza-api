package com.luanpaiva.localizaapi.adapter.output.repository.jpa;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.ReservaEntity;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepositoryJpa extends JpaRepository<ReservaEntity, Long> {

    List<ReservaEntity> findByStatusReserva(StatusReserva statusReserva);
}
