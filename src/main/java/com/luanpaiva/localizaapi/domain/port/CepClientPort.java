package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.response.CepResponse;

public interface CepClientPort {

    CepResponse consultarCep(String cep);
}
