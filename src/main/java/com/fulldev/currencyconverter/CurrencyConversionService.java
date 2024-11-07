package com.fulldev.currencyconverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Esta clase se encarga de la lógica para interactuar con la API externa de tasas de cambio
@Service
public class CurrencyConversionService {

    // Inyecta la URL base de la API desde el archivo de configuración
    @Value("${api.url}")
    private String apiUrl;

    // Inyecta RestTemplate para hacer solicitudes HTTP
    private final RestTemplate restTemplate;

    // Constructor donde se inyecta RestTemplate
    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Metodo que obtiene las tasas de cambio desde la API externa
    public ExchangeRateResponse getExchangeRates(String baseCurrency) {
        // Concatenamos la URL de la API con el parámetro base
        String url = apiUrl + baseCurrency;

        // Hacemos una solicitud GET a la API y obtenemos la respuesta
        return restTemplate.getForObject(url, ExchangeRateResponse.class);
    }
}
