package com.codecool.stockapp.controller;

import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.currency.CurrencyDetails;
import com.codecool.stockapp.model.Util;
import com.codecool.stockapp.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @GetMapping("/trade")
    public CurrencyDetails getCurrencyByID(@RequestParam int id) {
        return trader.getCurrencyById(id);
    }

    @GetMapping("/orders")
    public Set<Transaction> getOrders() {
        return trader.getTransactions();
    }

    @PostMapping("/buy")
    public void buy(@RequestBody Transaction transaction) {
        transaction.setDate(Util.getCurrentDate());
        trader.buy(transaction);
        System.out.println(trader.getTransactions());
    }

    @PostMapping("/sell")
    public void sell(@RequestBody Transaction transaction) {
        trader.sell(transaction);
    }
}
