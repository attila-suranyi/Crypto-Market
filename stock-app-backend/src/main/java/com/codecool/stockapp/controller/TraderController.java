package com.codecool.stockapp.controller;

import com.codecool.stockapp.model.CryptoCurrency;
import com.codecool.stockapp.model.DataItem;
import com.codecool.stockapp.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@CrossOrigin
@RestController
public class TraderController {

    @Autowired
    private Trader trader;

    @GetMapping("/")
    public CryptoCurrency showCryptoCurrencies() {
        return trader.getCurrencies();
    }

    @GetMapping("/btc")
    public Stream<DataItem> getCurrencyByName() {
        return trader.getCurrency("BTC");
    }
}
