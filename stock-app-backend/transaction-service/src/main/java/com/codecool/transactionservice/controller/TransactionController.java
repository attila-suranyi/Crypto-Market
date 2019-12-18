package com.codecool.transactionservice.controller;


import com.codecool.cryptomarketjsonclasses.model.Wallet;
import com.codecool.cryptomarketjsonclasses.model.currency.CryptoCurrency;
import com.codecool.cryptomarketjsonclasses.model.currency.CurrencyDetails;
import com.codecool.cryptomarketjsonclasses.model.transaction.OpenTransaction;
import com.codecool.cryptomarketjsonclasses.model.transaction.Transaction;
import com.codecool.cryptomarketjsonclasses.model.transaction.TransactionType;
import com.codecool.transactionservice.service.TransactionService;
import com.codecool.transactionservice.service.UserCaller;
import com.codecool.transactionservice.service.WalletCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {

    @Autowired
    private UserCaller userCaller;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WalletCaller walletCaller;

    @GetMapping("/")
    public CryptoCurrency getCryptoCurrencies() {
        return transactionService.getCurrencies("default", "default");
    }

    @GetMapping("/sorted")
    public CryptoCurrency getSortedCryptoCurrencies(@RequestParam String sort_by, @RequestParam String sort_dir) {
        return transactionService.getCurrencies(sort_by, sort_dir);
    }

    @GetMapping("/trade")
    public CurrencyDetails getCurrencyByID(@RequestParam int id) {
        return transactionService.getCurrencyById(id);
    }

    @GetMapping("/orders")
    public List<Transaction> getOrders() {
        return transactionService.getTransactions();
    }

    @PostMapping("/buy")
    public boolean buy(@RequestBody Transaction transaction, @RequestParam long userId) {
        transaction.setTransactionType(TransactionType.BUY);
        return transactionService.buy(transaction, userId);
    }

    @PostMapping("/sell")
    public boolean sell(@RequestBody Transaction transaction, @RequestParam long userId) {
        transaction.setTransactionType(TransactionType.SELL);
        return transactionService.sell(transaction, userId);
    }

    @GetMapping("/wallet")
    public Wallet[] showWallet(@RequestParam Long userId) {
        return walletCaller.getWalletList(userId);
    }

    @GetMapping("/open_order")
    public List<OpenTransaction> getOpenOrders(@RequestParam Long userId) {
        return transactionService.getOpenTransactions(userId);
    }

    @GetMapping("/order_history")
    public List<Transaction> getTransactionHistory(@RequestParam Long userId) {
        return transactionService.getTransactionHistoryByUserId(userId);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam Long userId) {
        return userCaller.getBalance(userId);
    }
}
