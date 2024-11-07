package com.fulldev.currencyconverter; // Especifica el paquete de la clase

import org.springframework.boot.SpringApplication; // Importa la clase para lanzar la aplicación
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación principal de Spring Boot

// Anotación que marca la clase como punto de entrada de la aplicación Spring Boot
@SpringBootApplication
public class CurrencyConverterApplication {

    // Metodo principal que se ejecuta al iniciar la aplicación
    public static void main(String[] args) {
        // Llama a SpringApplication.run() para iniciar la aplicación Spring Boot
        // El primer parámetro es la clase principal y el segundo son los argumentos de la línea de comandos
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }
}
