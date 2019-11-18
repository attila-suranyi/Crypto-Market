package com.codecool.stockapp.service;

import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.currency.CurrencyDetails;
import com.codecool.stockapp.model.entity.currency.SingleCurrency;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class Trader {

    @Autowired
    CurrencyAPIService currencyAPIService;

    private Set<Transaction> transactions = new HashSet<>();

    public Trader() {
    }

    public void buy(Transaction transaction) {
        transactions.add(transaction);
    }

    public void sell(Transaction transaction) {
        transactions.remove(transaction);
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {
        return currencyAPIService.getCurrencies(sortBy, sortDir);
    }

    public CurrencyDetails getCurrencyById(int id) {
        SingleCurrency currency = currencyAPIService.getSingleCurrency(id);
        return currency.getData().get(id);
    }
}
