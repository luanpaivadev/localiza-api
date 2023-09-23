package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.response.CepResponse;
import com.luanpaiva.localizaapi.domain.exception.CepInvalidoException;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.port.CepClientPort;
import com.luanpaiva.localizaapi.domain.port.CepServicePort;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;

public class CepService implements CepServicePort {

    private final CepClientPort cepClientPort;
    private final ModelMapperPort<CepResponse, Cliente.Endereco> modelMapperPort;

    public CepService(CepClientPort cepClientPort, ModelMapperPort<CepResponse, Cliente.Endereco> modelMapperPort) {
        this.cepClientPort = cepClientPort;
        this.modelMapperPort = modelMapperPort;
    }

    @Override
    public Cliente.Endereco consultarEnderecoPorCep(String cep) {

        cep = cep.replaceAll("\\D", "");

        if (cep.length() != 8) {
            throw new CepInvalidoException("Cep inválido");
        }

        CepResponse cepResponse = cepClientPort.consultarCep(cep);
        return modelMapperPort.map(cepResponse, Cliente.Endereco.class);
    }
}
