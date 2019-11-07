package com.codecool.stockapp.controller;

import com.codecool.stockapp.model.Currencies.CryptoCurrency;
import com.codecool.stockapp.model.Currencies.DataItem;
import com.codecool.stockapp.model.Order.Order;
import com.codecool.stockapp.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return trader.getCurrencies();
    }

    @GetMapping("/sorted")
    public CryptoCurrency getSortedCryptoCurrencies(@RequestParam String sort_by, @RequestParam String sort_dir) {
        return trader.getCurrencies(sort_by, sort_dir);
    }

    @GetMapping("/{symbol}")
    public Stream<Double> getCurrency(@PathVariable String symbol) {
        return trader.getCurrencies().getData().stream().filter(x -> x.getSymbol().equals(symbol)).map(x -> x.getQuote().getUSD().getPrice());
    }

    @GetMapping("/orders")
    public Set<Order> getOrders() {
        return trader.getOrders();
    }

    @PostMapping("/buy")
    public void buy(@RequestBody Order order) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        order.setDate(dateFormat.format(new Date()));
        System.out.println(trader.getOrders());
        trader.buy(order);
    }

    @PostMapping("/sell")
    public void sell(@RequestBody Order order) {
        trader.sell(order);
    }
}
