package com.luanpaiva.localizaapi.adapter.config.configbeans;

import com.luanpaiva.localizaapi.domain.port.CepClientPort;
import com.luanpaiva.localizaapi.domain.port.CepServicePort;
import com.luanpaiva.localizaapi.domain.service.CepService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CepServiceConfig {

    @Bean
    public CepServicePort cepServicePort(CepClientPort cepClientPort) {
        return new CepService(cepClientPort);
    }
}
