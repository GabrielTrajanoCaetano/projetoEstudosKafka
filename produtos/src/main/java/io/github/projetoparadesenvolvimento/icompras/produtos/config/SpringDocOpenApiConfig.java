package io.github.projetoparadesenvolvimento.icompras.produtos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("REST API - Spring Kafka controle de pedidos")
                                .description("Api destinada ao controle de pedidos, desde inicio de cadastro do produto, ao preparo para entrega com uso de ferramentas como kafka")
                                .version("v1")
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                                .contact(new Contact().name("Gabriel Trajano Caetano").email("gabriel.caetanotrajano@hotmail.com"))
                );
    }
}
