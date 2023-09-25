package com.luanpaiva.localizaapi.adapter.output.repository;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.ClienteRespositoryJpa;
import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.ClienteEntity;
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
        Optional<ClienteEntity> clienteEntityOptional = clienteRespositoryJpa.findByCpf(cpf);
        return clienteEntityOptional.map(ClienteEntity::toCliente);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.toClienteEntity(cliente);
        clienteRespositoryJpa.save(clienteEntity);
        return clienteEntity.toCliente();
    }
}
