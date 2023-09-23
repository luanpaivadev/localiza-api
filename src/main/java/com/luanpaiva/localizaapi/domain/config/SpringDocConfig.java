package com.luanpaiva.localizaapi.domain.config;

import com.luanpaiva.localizaapi.domain.config.properties.InfoAppProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringDocConfig {

    private final InfoAppProperties properties;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(properties.getName())
                        .version(properties.getVersion())
                        .description(properties.getDescription())
                );
    }
}
