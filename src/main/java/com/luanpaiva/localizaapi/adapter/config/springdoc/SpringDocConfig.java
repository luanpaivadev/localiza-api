package com.luanpaiva.localizaapi.adapter.config.springdoc;

import com.luanpaiva.localizaapi.adapter.config.properties.InfoAppProperties;
import com.luanpaiva.localizaapi.adapter.input.api.v1.handler.model.ResponseError;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
                ).components(new Components().schemas(gerarSchemas()));
    }

    private Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();
        Map<String, Schema> responseErrorSchema = ModelConverters.getInstance().read(ResponseError.class);
        schemaMap.putAll(responseErrorSchema);
        return schemaMap;
    }
}
