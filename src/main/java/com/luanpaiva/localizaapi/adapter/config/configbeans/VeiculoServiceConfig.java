package com.luanpaiva.localizaapi.adapter.config.configbeans;

import com.luanpaiva.localizaapi.domain.port.VeiculoRepositoryPort;
import com.luanpaiva.localizaapi.domain.port.VeiculoServicePort;
import com.luanpaiva.localizaapi.domain.service.VeiculoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VeiculoServiceConfig {

    @Bean
    public VeiculoServicePort veiculoServicePort(VeiculoRepositoryPort veiculoRepositoryPort) {
        return new VeiculoService(veiculoRepositoryPort);
    }
}
