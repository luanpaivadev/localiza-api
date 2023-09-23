package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.domain.exception.CepInvalidoException;
import com.luanpaiva.localizaapi.api.v1.model.response.CepResponse;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.repository.CepClient;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CepService {

    private final CepClient cepClient;
    private final ModelMapper modelMapper;

    public Cliente.Endereco consultarEnderecoPorCep(String cep) {

        cep = cep.replaceAll("\\D", "");

        if (cep.length() != 8) {
            throw new CepInvalidoException("Cep inválido");
        }

        CepResponse cepResponse = cepClient.consultarCep(cep);
        return modelMapper.map(cepResponse, Cliente.Endereco.class);
    }
}
