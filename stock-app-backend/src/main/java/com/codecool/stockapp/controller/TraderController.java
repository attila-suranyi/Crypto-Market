package com.codecool.stockapp.controller;

import com.codecool.stockapp.service.Trader;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class TraderController {

    @Autowired
    private Trader trader;

    @GetMapping("/")
    public String print() throws IOException, URISyntaxException, JSONException {
        return trader.getTopCurrency(10);
    }
}
