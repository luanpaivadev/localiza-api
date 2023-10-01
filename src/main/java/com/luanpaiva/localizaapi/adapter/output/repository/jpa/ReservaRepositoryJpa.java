package com.luanpaiva.localizaapi.adapter.output.repository.jpa;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.ReservaEntity;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepositoryJpa extends JpaRepository<ReservaEntity, Long> {

    @Query("FROM ReservaEntity r JOIN FETCH r.veiculo JOIN FETCH r.cliente c JOIN FETCH c.endereco WHERE r.statusReserva = ?1")
    List<ReservaEntity> findByStatusReserva(StatusReserva statusReserva);
}
