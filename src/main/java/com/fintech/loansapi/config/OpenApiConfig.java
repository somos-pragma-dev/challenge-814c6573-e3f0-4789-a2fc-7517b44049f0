package com.fintech.loansapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:" + serverPort);
        server.setDescription("Servidor de desarrollo local");

        Contact contact = new Contact();
        contact.setName("Equipo de Desarrollo Fintech");
        contact.setEmail("dev@fintech.com");
        contact.setUrl("https://www.fintech.com");

        License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info();
        info.setTitle("Loans API - Fintech");
        info.setVersion("1.0.0");
        info.setDescription("""
                API REST para la gestión de préstamos en la plataforma Fintech.
                
                Esta API permite realizar operaciones CRUD completas sobre préstamos,
                incluyendo creación, consulta, actualización y eliminación de registros.
                
                ## Autenticación
                Currently, this API uses basic authentication. Future versions will include
                OAuth 2.0 support for enhanced security.
                
                ## Rate Limiting
                Se aplica un límite de 1000 peticiones por hora por cada cliente.
                
                ## Códigos de Estado
                - **200 OK**: La operación fue exitosa
                - **201 Created**: El recurso fue creado correctamente
                - **400 Bad Request**: Datos de entrada inválidos
                - **404 Not Found**: El recurso no existe
                - **500 Internal Server Error**: Error interno del servidor
                """);
        info.setContact(contact);
        info.setLicense(license);

        Components components = new Components();

        return new OpenAPI()
                .info(info)
                .servers(List.of(server))
                .components(components);
    }

    @Bean
    public io.swagger.v3.oas.models.security.SecurityScheme securityScheme() {
        io.swagger.v3.oas.models.security.SecurityScheme securityScheme =
            new io.swagger.v3.oas.models.security.HttpSecurityScheme()
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization");
        return securityScheme;
    }
}