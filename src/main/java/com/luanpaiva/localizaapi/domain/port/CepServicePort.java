package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Endereco;

public interface CepServicePort {

    Endereco consultarEnderecoPorCep(String cep);
}
