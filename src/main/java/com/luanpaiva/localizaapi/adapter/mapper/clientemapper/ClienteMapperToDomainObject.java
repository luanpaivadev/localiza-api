package com.luanpaiva.localizaapi.adapter.mapper.clientemapper;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.ClienteInput;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClienteMapperToDomainObject implements ModelMapperPort<ClienteInput, Cliente> {

    private final ModelMapper modelMapper;

    @Override
    public Cliente map(ClienteInput clienteInput, Class<Cliente> clienteClass) {
        return modelMapper.map(clienteInput, clienteClass);
    }

    @Override
    public List<Cliente> map(List<ClienteInput> clienteInputList, Class<Cliente> clienteClass) {
        return clienteInputList.stream().map(clienteInput -> modelMapper.map(clienteInput, clienteClass)).toList();
    }
}
