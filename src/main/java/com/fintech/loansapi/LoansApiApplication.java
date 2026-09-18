package com.fintech.loansapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Punto de entrada principal de la aplicación Loans API.
 * 
 * Esta clase inicia el contexto de Spring Boot y configura todos los
 * componentes necesarios para el funcionamiento de la API REST de préstamos.
 * La anotación @SpringBootApplication combina tres anotaciones principales:
 * - @Configuration: marca la clase como fuente de definiciones de beans
 * - @EnableAutoConfiguration: habilita la configuración automática de Spring
 * - @ComponentScan: permite el escaneo de componentes en el paquete actual
 * 
 * El proyecto sigue una arquitectura de capas estándar:
 * - Controller: capa de presentación que maneja las peticiones HTTP
 * - Service: capa de lógica de negocio que contiene las reglas del dominio
 * - Repository: capa de acceso a datos que interactúa con la base de datos
 * 
 * La base de datos H2 se configura en modo desarrollo para facilitar
 * las pruebas y el desarrollo sin necesidad de una base de datos externa.
 */
@SpringBootApplication
public class LoansApiApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(LoansApiApplication.class);
    
    public static void main(String[] args) {
        logger.info("Iniciando la aplicación de Gestión de Préstamos...");
        SpringApplication.run(LoansApiApplication.class, args);
        logger.info("Aplicación iniciada correctamente. Swagger disponible en /swagger-ui.html");
    }
    
    /**
     * Bean de inicialización que se ejecuta al arranque de la aplicación.
     * Se utiliza para mostrar información de configuración y realizar
     * verificaciones iniciales del sistema.
     */
    @Bean
    public CommandLineRunner init() {
        return args -> {
            logger.info("=== Sistema de Gestión de Préstamos ===");
            logger.info("Versión de Java: {}", System.getProperty("java.version"));
            logger.info("Perfil activo: {}", System.getProperty("spring.profiles.active", "default"));
            logger.info("Puerto del servidor: {}", System.getProperty("server.port", "8080"));
            logger.info("Base de datos H2 inicializada en memoria");
            logger.info("Documentación Swagger disponible en: /swagger-ui.html");
            logger.info("Documentación OpenAPI JSON disponible en: /v3/api-docs");
        };
    }
}