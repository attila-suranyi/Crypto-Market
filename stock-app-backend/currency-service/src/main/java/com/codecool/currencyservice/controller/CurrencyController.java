package com.codecool.currencyservice.controller;

import com.codecool.currencyservice.model.currency.CryptoCurrency;
import com.codecool.currencyservice.model.currency.SingleCurrency;
import com.codecool.currencyservice.service.CurrencyApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyApiCaller currencyApiCaller;

    @GetMapping("/all")
    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {
        return currencyApiCaller.getCurrencies(sortBy, sortDir);
    }

    @GetMapping
    public SingleCurrency getSingleCurrency(Long id) {
        return currencyApiCaller.getSingleCurrency(id);
    }

    @GetMapping("/price")
    public Double getSingleCurrencyPrice(Long id) {
        return currencyApiCaller.getSingleCurrencyPrice(id);
    }
}
