package com.luanpaiva.localizaapi.adapter.config.configbeans;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.response.CepResponse;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.port.CepClientPort;
import com.luanpaiva.localizaapi.domain.port.CepServicePort;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import com.luanpaiva.localizaapi.domain.service.CepService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CepServiceConfig {

    @Bean
    public CepServicePort cepServicePort(CepClientPort cepClientPort, ModelMapperPort<CepResponse, Cliente.Endereco> modelMapperPort) {
        return new CepService(cepClientPort, modelMapperPort);
    }
}
