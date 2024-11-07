package com.fulldev.currencyconverter;

import java.util.Map;

// Esta clase representa la estructura de la respuesta JSON de la API de tasas de cambio
public class ExchangeRateResponse {

    // Estado de la solicitud (por ejemplo, "success" o "error")
    private String result;

    // Código de la moneda base (por ejemplo, "USD")
    private String base_code;

    // Mapa de tasas de cambio para diferentes monedas
    private Map<String, Double> conversion_rates;

    // Getters y Setters para cada campo

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
