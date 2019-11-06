package com.codecool.stockapp.service.api;

import com.codecool.stockapp.model.CryptoCurrency;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyAPIService {

    private String latestCurrenciesURL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private String apiKey = "047211f4-eaa0-44c7-9801-cd2545e77cf0";

    public CryptoCurrency getCurrencies() {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add("X-CMC_PRO_API_KEY", this.apiKey );

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                this.latestCurrenciesURL,
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                CryptoCurrency.class
        ).getBody();
    }

    public CryptoCurrency getSortedCurrencies(String sortBy, String sortDir) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add("X-CMC_PRO_API_KEY", this.apiKey );

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.latestCurrenciesURL)
                .queryParam("sort", sortBy)
                .queryParam("sort_dir", sortDir);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                CryptoCurrency.class
        ).getBody();
    }
}
