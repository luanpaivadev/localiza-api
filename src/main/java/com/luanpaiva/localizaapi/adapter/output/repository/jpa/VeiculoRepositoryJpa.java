package com.luanpaiva.localizaapi.adapter.output.repository.jpa;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepositoryJpa extends JpaRepository<VeiculoEntity, Long> {

    Optional<VeiculoEntity> findByPlaca(String placa);
}
