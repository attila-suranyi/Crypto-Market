package com.codecool.stockapp.controller;

import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.entity.Wallet;
import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
import com.codecool.stockapp.model.entity.currency.CurrencyDetails;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.transaction.TransactionType;
import com.codecool.stockapp.model.repository.UserRepository;
import com.codecool.stockapp.service.Trader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TraderController {

    @Autowired
    private Trader trader;


    @GetMapping("/")
    public CryptoCurrency getCryptoCurrencies() {
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
    public List<Transaction> getOrders() {
        return trader.getTransactions();
    }

    @PostMapping("/buy")
    public boolean buy(@RequestBody Transaction transaction) {
        transaction.setTransactionType(TransactionType.BUY);
        return trader.buy(transaction, 1);
    }

    @PostMapping("/sell")
    public boolean sell(@RequestBody Transaction transaction) {
        transaction.setTransactionType(TransactionType.SELL);
        return trader.sell(transaction, 1);
    }

    @GetMapping("/wallet")
    public List<Wallet> showWallet(@RequestParam int id) {
        return trader.getWallet(id);
    }

    //TODO this piece seems wrong
    @GetMapping("/open_order")
    public String getOpenOrders(@RequestParam Long userId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(trader.getOpenTransactions(userId));
    }

    @GetMapping("/order_history")
    public String getTransactionHistory(@RequestParam Long userId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(trader.getTransactionHistoryByUserId(userId));
    }
}
