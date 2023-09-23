package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Cliente;

public interface CepServicePort {

    Cliente.Endereco consultarEnderecoPorCep(String cep);
}
