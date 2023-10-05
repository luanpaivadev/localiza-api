package com.luanpaiva.localizaapi.adapter.config.configbeans;

import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import com.luanpaiva.localizaapi.domain.port.EnvioEmailPort;
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
                                                 VeiculoServicePort veiculoServicePort,
                                                 EnvioEmailPort envioEmailPort) {

        return new ReservaService(reservaRepositoryPort, clienteServicePort, veiculoServicePort, envioEmailPort);
    }
}
