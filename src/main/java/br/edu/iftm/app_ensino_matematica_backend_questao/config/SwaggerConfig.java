package br.edu.iftm.app_ensino_matematica_backend_questao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;



@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8083").description("Local server"))
                .addServersItem(new Server().url("https://app-matematica-backend-questao-3a364d6ca0e2.herokuapp.com").description("Production server"))
                .info(new Info()
                        .title("API Microserviço Questão - App Matemática")
                        .version("1.0.0")
                        .description("API para gerenciamento de questões e categorias do aplicativo de ensino de matemática"));
    }
}
