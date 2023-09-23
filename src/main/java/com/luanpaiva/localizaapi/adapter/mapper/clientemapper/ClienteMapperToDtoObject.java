package com.luanpaiva.localizaapi.adapter.mapper.clientemapper;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.ClienteDto;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClienteMapperToDtoObject implements ModelMapperPort<Cliente, ClienteDto> {

    private final ModelMapper modelMapper;

    @Override
    public ClienteDto map(Cliente cliente, Class<ClienteDto> clienteDtoClass) {
        return modelMapper.map(cliente, clienteDtoClass);
    }

    @Override
    public List<ClienteDto> map(List<Cliente> clienteList, Class<ClienteDto> clienteDtoClass) {
        return clienteList.stream().map(cliente -> modelMapper.map(cliente, clienteDtoClass)).toList();
    }
}
