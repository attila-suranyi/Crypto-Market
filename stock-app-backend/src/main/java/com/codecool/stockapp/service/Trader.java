package com.codecool.stockapp.service;

import com.codecool.stockapp.model.CryptoAPIService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class Trader {
    private CryptoAPIService cryptoAPIService;

    @Autowired
    public Trader(CryptoAPIService cryptoAPIService) {
        this.cryptoAPIService = cryptoAPIService;
    }
    public String getPrice() throws IOException, URISyntaxException, JSONException {
        return cryptoAPIService.getPrice("BTC");
    }
}
