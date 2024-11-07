package com.fulldev.currencyconverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marca la clase como una clase de configuración de Spring
public class CorsConfig implements WebMvcConfigurer {

    // Metodo que configura las reglas de CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Establecemos las reglas de CORS para permitir solicitudes desde cualquier origen
        registry.addMapping("/**") // Acepta solicitudes a cualquier ruta
                .allowedOrigins("http://localhost:63342") // Permite solicitudes desde el origen del frontend (puedes añadir más si es necesario)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite los métodos HTTP que deseas permitir
                .allowedHeaders("*") // Permite cualquier tipo de cabeceras
                .allowCredentials(true); // Permite enviar cookies o cabeceras de autenticación
    }
}
