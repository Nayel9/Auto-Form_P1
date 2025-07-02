package com.autoform.commandes;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestionnaire de Commandes BigMeat")
                        .version("1.0")
                        .description("API pour la gestion des commandes internes de l'entreprise BigMeat (bootcamp projet 1)")
                );
    }
}
