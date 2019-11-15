package com.codecool.stockapp.service.api;

import com.codecool.stockapp.model.Currencies.CryptoCurrency;
import com.codecool.stockapp.model.SingleCurrency.SingleCurrency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class CurrencyAPIService {

    @Value("${crypto.apikey}")
    private String apiKey;

    @Value("${crypto.latestCryptoEndpoint}")
    private String latestCryptoAPIEndpoint;

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add("X-CMC_PRO_API_KEY", this.apiKey);

        String apiEndpoint;

        if (sortBy.equals("default")) {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.latestCryptoAPIEndpoint)
                    .queryParam("limit", 20);
            apiEndpoint = uriBuilder.toUriString();
        } else {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.latestCryptoAPIEndpoint)
                    .queryParam("sort", sortBy)
                    .queryParam("sort_dir", sortDir)
                    .queryParam("limit", 20);
            apiEndpoint = uriBuilder.toUriString();
        }

        System.out.println(apiEndpoint);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                apiEndpoint,
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                CryptoCurrency.class
        ).getBody();
    }

    public SingleCurrency getSingleCurrency(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add("X-CMC_PRO_API_KEY", this.apiKey);

        String apiEndpoint;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest")
                .queryParam("id", id);

        apiEndpoint = uriBuilder.toUriString();

        System.out.println(apiEndpoint);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(
                apiEndpoint,
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                SingleCurrency.class
        ).getBody();
    }
}


