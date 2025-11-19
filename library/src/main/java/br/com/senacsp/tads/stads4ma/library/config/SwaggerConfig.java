package br.com.senacsp.tads.stads4ma.library.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

     @Bean
     public OpenAPI customOpenAPI() {
          return new OpenAPI()
                  .info(new Info()
                          .title("API Meu Projeto")
                          .version("1.0")
                          .description("Documentação oficial da API")
                          .contact(new Contact()
                                  .name("Equipe de Desenvolvimento")
                                  .email("contato@meuprojeto.com")));
     }
}
