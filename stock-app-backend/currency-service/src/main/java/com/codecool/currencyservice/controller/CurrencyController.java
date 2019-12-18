package com.codecool.currencyservice.controller;

import com.codecool.cryptomarketjsonclasses.model.currency.CryptoCurrency;
import com.codecool.cryptomarketjsonclasses.model.currency.SingleCurrency;
import com.codecool.currencyservice.service.CurrencyApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyApiCaller currencyApiCaller;

    @GetMapping("/all")
    public CryptoCurrency getCurrencies(@RequestParam String sortBy, @RequestParam String sortDir) {
        return currencyApiCaller.getCurrencies(sortBy, sortDir);
    }

    @GetMapping
    public SingleCurrency getSingleCurrency(@RequestParam Long id) {
        return currencyApiCaller.getSingleCurrency(id);
    }

    @GetMapping("/price")
    public Double getSingleCurrencyPrice(@RequestParam Long id) {
        return currencyApiCaller.getSingleCurrencyPrice(id);
    }
}
