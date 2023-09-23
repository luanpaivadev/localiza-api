package com.luanpaiva.localizaapi.adapter.output.repository;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.ClienteRespositoryJpa;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.port.ClienteRespositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ClienteRespositoryJpaAdapter implements ClienteRespositoryPort {

    private final ClienteRespositoryJpa clienteRespositoryJpa;

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return clienteRespositoryJpa.findByCpf(cpf);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRespositoryJpa.save(cliente);
    }
}
