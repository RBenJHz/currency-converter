package com.fulldev.currencyconverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Configuración para crear un bean de RestTemplate
@Configuration
public class AppConfig {

    // Metodo que define el bean RestTemplate para ser utilizado en el proyecto
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // Devuelve una nueva instancia de RestTemplate
    }
}
