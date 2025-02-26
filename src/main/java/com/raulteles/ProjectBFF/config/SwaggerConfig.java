package com.raulteles.ProjectBFF.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Carrega o arquivo openapi.yml
        return new OpenAPIV3Parser().read("openapi.yml");
    }

}
