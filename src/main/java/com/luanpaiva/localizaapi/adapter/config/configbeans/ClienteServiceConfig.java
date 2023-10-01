package com.luanpaiva.localizaapi.adapter.config.configbeans;

import com.luanpaiva.localizaapi.domain.port.CepServicePort;
import com.luanpaiva.localizaapi.domain.port.ClienteRespositoryPort;
import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import com.luanpaiva.localizaapi.domain.service.ClienteService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteServiceConfig {

    @Bean
    public ClienteServicePort clienteServicePort(ClienteRespositoryPort clienteRespositoryPort, CepServicePort cepServicePort, MeterRegistry meterRegistry) {
        return new ClienteService(clienteRespositoryPort, cepServicePort, meterRegistry);
    }
}
