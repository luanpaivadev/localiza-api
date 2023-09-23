package com.luanpaiva.localizaapi.adapter.output.openfeign;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.response.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepClientFeign {

    @GetMapping("/{cep}/json")
    CepResponse consultarCep(@PathVariable String cep);
}
