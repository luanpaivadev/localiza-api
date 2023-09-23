package com.luanpaiva.localizaapi.adapter.output.openfeign;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.response.CepResponse;
import com.luanpaiva.localizaapi.domain.port.CepClientPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CepClientFeignAdapter implements CepClientPort {

    private final CepClientFeign cepClientFeign;

    @Override
    public CepResponse consultarCep(String cep) {
        return cepClientFeign.consultarCep(cep);
    }
}
