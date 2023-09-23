package com.luanpaiva.localizaapi.adapter.mapper.cepmapper;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.response.CepResponse;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CepResponseToEnderecoDomain implements ModelMapperPort<CepResponse, Cliente.Endereco> {

    private final ModelMapper modelMapper;

    @Override
    public Cliente.Endereco map(CepResponse cepResponse, Class<Cliente.Endereco> enderecoClass) {
        return modelMapper.map(cepResponse, enderecoClass);
    }

    @Override
    public List<Cliente.Endereco> map(List<CepResponse> cepResponseList, Class<Cliente.Endereco> enderecoClass) {
        return cepResponseList.stream().map(cepResponse -> modelMapper.map(cepResponse, enderecoClass)).toList();
    }
}
