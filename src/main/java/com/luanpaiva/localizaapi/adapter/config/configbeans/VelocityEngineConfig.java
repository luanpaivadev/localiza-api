package com.luanpaiva.localizaapi.adapter.config.configbeans;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VelocityEngineConfig {

    @Bean
    public VelocityEngine velocityEngine() {
        return new VelocityEngine();
    }
}
