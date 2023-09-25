package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.response.CepResponse;
import com.luanpaiva.localizaapi.domain.model.Endereco;
import com.luanpaiva.localizaapi.domain.port.CepClientPort;
import com.luanpaiva.localizaapi.domain.port.CepServicePort;

public class CepService implements CepServicePort {

    private final CepClientPort cepClientPort;

    public CepService(CepClientPort cepClientPort) {
        this.cepClientPort = cepClientPort;
    }

    @Override
    public Endereco consultarEnderecoPorCep(String cep) {
        CepResponse cepResponse = cepClientPort.consultarCep(cep);
        return cepResponse.toEndereco();
    }
}
