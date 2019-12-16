package com.codecool.temp.controller;

import com.codecool.temp.model.entity.Wallet;
import com.codecool.temp.model.entity.currency.CryptoCurrency;
import com.codecool.temp.model.entity.currency.CurrencyDetails;
import com.codecool.temp.model.entity.transaction.OpenTransaction;
import com.codecool.temp.model.entity.transaction.Transaction;
import com.codecool.temp.model.entity.transaction.TransactionType;
import com.codecool.temp.service.Trader;
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

    //TODO do we use this?
    @GetMapping("/orders")
    public List<Transaction> getOrders() {
        return trader.getTransactions();
    }

    @PostMapping("/buy")
    public boolean buy(@RequestBody Transaction transaction, @RequestParam long userId) {
        transaction.setTransactionType(TransactionType.BUY);
        return trader.buy(transaction, userId);
    }

    @PostMapping("/sell")
    public boolean sell(@RequestBody Transaction transaction, @RequestParam long userId) {
        transaction.setTransactionType(TransactionType.SELL);
        return trader.sell(transaction, userId);
    }

    @GetMapping("/wallet")
    public List<Wallet> showWallet(@RequestParam Long userId) {
        return trader.getWallet(userId);
    }

    @GetMapping("/open_order")
    public List<OpenTransaction> getOpenOrders(@RequestParam Long userId) {
        return trader.getOpenTransactions(userId);
    }

    @GetMapping("/order_history")
    public List<Transaction> getTransactionHistory(@RequestParam Long userId) {
        return trader.getTransactionHistoryByUserId(userId);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam Long userId) {
        return trader.getBalance(userId);
    }
}
