package com.muralis.sistema.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de despesas")
                        .version("v1")
                        .description("API para gerenciamento de despesas para o teste técnico da vaga de Pleno Fullstack"));
    }
}
