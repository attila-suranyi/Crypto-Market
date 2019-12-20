package com.codecool.wallet.service;


import com.codecool.cryptomarketjsonclasses.model.currency.CryptoCurrency;
import com.codecool.cryptomarketjsonclasses.model.currency.SingleCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyCaller {

    @Autowired
    private RestTemplate restTemplate;

    public SingleCurrency getCurrency(Long id) {
        return restTemplate.getForEntity("http://currency-service/currency?id=" + id, SingleCurrency.class).getBody();
    }

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {
        return restTemplate.getForEntity("http://currency-service/currency/all?sortBy=" + sortBy + "&sortDir=" + sortDir, CryptoCurrency.class).getBody();
    }

    public Double getSingleCurrencyPrice(Long id) {
        return restTemplate.getForEntity("http://currency-service/currency/price?id=" + id, Double.class).getBody();
    }
}
