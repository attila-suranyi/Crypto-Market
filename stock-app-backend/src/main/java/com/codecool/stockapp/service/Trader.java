package com.codecool.stockapp.service;

import com.codecool.stockapp.model.CryptoCurrency;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Trader {

    @Autowired
    CurrencyAPIService currencyAPIService;

    public ResponseEntity<CryptoCurrency> getCurrencies() {
        return currencyAPIService.getCurrencies();
    }
}
