package com.luanpaiva.localizaapi.adapter.config.configbeans;

import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import com.luanpaiva.localizaapi.domain.port.ReservaRepositoryPort;
import com.luanpaiva.localizaapi.domain.port.ReservaServicePort;
import com.luanpaiva.localizaapi.domain.port.VeiculoServicePort;
import com.luanpaiva.localizaapi.domain.service.ReservaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaServiceConfig {

    @Bean
    public ReservaServicePort reservaServicePort(ReservaRepositoryPort reservaRepositoryPort,
                                                 ClienteServicePort clienteServicePort,
                                                 VeiculoServicePort veiculoServicePort) {

        return new ReservaService(reservaRepositoryPort, clienteServicePort, veiculoServicePort);
    }
}
