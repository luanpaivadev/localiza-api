package com.luanpaiva.localizaapi.adapter.output.repository.jpa;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRespositoryJpa extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf);
}
