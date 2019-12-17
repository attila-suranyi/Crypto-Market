package com.codecool.currencyservice.api;

import com.codecool.temp.model.entity.currency.CryptoCurrency;
import com.codecool.temp.model.entity.currency.SingleCurrency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CurrencyAPIService {

    @Value("${crypto.apikey}")
    private String apiKey;

    @Value("${crypto.latestAllCryptoEndpoint}")
    private String latestCryptoAPIEndpoint;

    @Value("${crypto.latestSingleCryptoEndpoint}")
    private String latestSingleCryptoData;

    private HttpHeaders buildHeaderWithAPIKey() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add("X-CMC_PRO_API_KEY", this.apiKey);
        return headers;
    }

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {

        String apiEndpoint;
        UriComponentsBuilder uriBuilder;

        if (sortBy.equals("default")) {
            uriBuilder = UriComponentsBuilder.fromHttpUrl(this.latestCryptoAPIEndpoint)
                    .queryParam("limit", 20);
        } else {
            uriBuilder = UriComponentsBuilder.fromHttpUrl(this.latestCryptoAPIEndpoint)
                    .queryParam("sort", sortBy)
                    .queryParam("sort_dir", sortDir)
                    .queryParam("limit", 20);
        }

        apiEndpoint = uriBuilder.toUriString();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                apiEndpoint,
                HttpMethod.GET,
                new HttpEntity<>("parameters", this.buildHeaderWithAPIKey()),
                CryptoCurrency.class
        ).getBody();
    }

    public SingleCurrency getSingleCurrency(Long id) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.latestSingleCryptoData)
                .queryParam("id", id);

        String apiEndpoint = uriBuilder.toUriString();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(
                apiEndpoint,
                HttpMethod.GET,
                new HttpEntity<>("parameters", this.buildHeaderWithAPIKey()),
                SingleCurrency.class
        ).getBody();
    }

    public Double getSingleCurrencyPrice(Long id) {
        SingleCurrency currency = this.getSingleCurrency(id);
        return currency.getData().get(id).getQuote().getUSD().getPrice();
    }
}


