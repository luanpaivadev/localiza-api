package com.luanpaiva.localizaapi.adapter.config.configbeans;

import com.luanpaiva.localizaapi.domain.port.AluguelRepositoryPort;
import com.luanpaiva.localizaapi.domain.port.AluguelServicePort;
import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import com.luanpaiva.localizaapi.domain.port.VeiculoServicePort;
import com.luanpaiva.localizaapi.domain.service.AluguelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AluguelServiceConfig {

    @Bean
    public AluguelServicePort aluguelService(AluguelRepositoryPort aluguelRepositoryPort,
                                             ClienteServicePort clienteServicePort,
                                             VeiculoServicePort veiculoServicePort) {

        return new AluguelService(aluguelRepositoryPort, clienteServicePort, veiculoServicePort);
    }
}
