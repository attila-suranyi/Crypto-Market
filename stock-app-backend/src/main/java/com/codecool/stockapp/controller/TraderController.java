package com.codecool.stockapp.controller;

import com.codecool.stockapp.model.Currencies.CryptoCurrency;
import com.codecool.stockapp.model.Currencies.DataItem;
import com.codecool.stockapp.model.Order.Order;
import com.codecool.stockapp.model.SingleCurrency.SingleCurrency;
import com.codecool.stockapp.model.Util;
import com.codecool.stockapp.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;

@CrossOrigin
@RestController
public class TraderController {

    @Autowired
    private Trader trader;

    @GetMapping("/")
    public CryptoCurrency showCryptoCurrencies() {
        return trader.getCurrencies("default", "default");
    }

    @GetMapping("/sorted")
    public CryptoCurrency getSortedCryptoCurrencies(@RequestParam String sort_by, @RequestParam String sort_dir) {
        return trader.getCurrencies(sort_by, sort_dir);
    }

    @GetMapping("/{symbol}")
    public Stream<Double> getCurrency(@PathVariable String symbol) {
        return trader.getCurrencies("default", "default").getData()
                .stream()
                .filter(x -> x.getSymbol().equals(symbol))
                .map(x -> x.getQuote().getUSD().getPrice());
    }

    @GetMapping("/trade")
    public SingleCurrency getCurrencyByID(@RequestParam int id) {
        return trader.getCurrencyById(id);
    }

    @GetMapping("/orders")
    public Set<Order> getOrders() {
        return trader.getOrders();
    }

    @PostMapping("/buy")
    public void buy(@RequestBody Order order) {
        order.setDate(Util.getCurrentDate());
        trader.buy(order);
        System.out.println(trader.getOrders());
    }

    @PostMapping("/sell")
    public void sell(@RequestBody Order order) {
        trader.sell(order);
    }
}
