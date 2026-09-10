package br.com.raimundo.estoque.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI estoqueOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("API de Estoque")
                                .description(
                                        "API REST para gerenciamento de produtos e movimentações de estoque"
                                )
                                .version("0.6.0")
                );
    }
}