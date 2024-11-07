package com.fulldev.currencyconverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CurrencyConverterController {

    // Inyecta la URL de la API desde application.properties
    @Value("${api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    /*
     * Endpoint para obtener la tasa de cambio de una moneda base a otra.
     * @param base La moneda base desde la que se convierte.
     * @param target La moneda a la que se desea convertir.
     * @return La tasa de cambio entre la moneda base y la moneda objetivo.
     */
    @GetMapping("/convert/{base}")
    public Double getExchangeRate(@PathVariable String base, @RequestParam String target) {
        // Construye la URL completa con el parámetro base
        String url = apiUrl + base;

        // Realiza la solicitud a la API para obtener la respuesta
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        // Verifica que la respuesta sea exitosa y contenga la tasa de cambio deseada
        if (response != null && "success".equals(response.getResult())) {
            Double rate = response.getConversion_rates().get(target);
            if (rate != null) {
                return rate;
            } else {
                throw new RuntimeException("Tasa de cambio no disponible para la moneda objetivo.");
            }
        } else {
            throw new RuntimeException("Error al obtener la respuesta de la API.");
        }
    }
}