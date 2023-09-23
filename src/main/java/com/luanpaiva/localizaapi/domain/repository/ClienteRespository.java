package com.luanpaiva.localizaapi.domain.repository;

import com.luanpaiva.localizaapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRespository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);
}
