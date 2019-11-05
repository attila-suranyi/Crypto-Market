package com.codecool.stockapp.service.api;

import com.codecool.stockapp.model.CryptoCurrency;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class CurrencyAPIService {

    public ResponseEntity<CryptoCurrency> getCurrencies() {

        String currenciesURL
                = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        String apiKey = "047211f4-eaa0-44c7-9801-cd2545e77cf0";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add("X-CMC_PRO_API_KEY", apiKey );


        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                currenciesURL,
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                CryptoCurrency.class
        );
    }
}
