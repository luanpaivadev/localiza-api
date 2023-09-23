package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRespositoryPort {

    Optional<Cliente> findByCpf(String cpf);

    Cliente save(Cliente cliente);
}
