package com.luanpaiva.localizaapi.domain.repository;

import com.luanpaiva.localizaapi.api.v1.model.response.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "viacep.com.br/ws")
public interface CepClient {

    @GetMapping("/{cep}/json")
    CepResponse consultarCep(@PathVariable String cep);
}
