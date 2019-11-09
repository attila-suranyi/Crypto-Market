package com.codecool.stockapp.service.api;

import com.codecool.stockapp.model.Currencies.CryptoCurrency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CurrencyAPIService {

    @Value("${crypto.apikey}")
    private String apiKey;

    @Value("${crypto.latestCryptoEndpoint}")
    private String latestCryptoAPIEndpoint;

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add("X-CMC_PRO_API_KEY", this.apiKey );

        String apiEndpoint;

        if (sortBy.equals("default")) {
            apiEndpoint = this.latestCryptoAPIEndpoint;
        } else {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.latestCryptoAPIEndpoint)
                    .queryParam("sort", sortBy)
                    .queryParam("sort_dir", sortDir);
            apiEndpoint = uriBuilder.toUriString();
        }

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                apiEndpoint,
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                CryptoCurrency.class
        ).getBody();
    }
}
