package com.codecool.stockapp.service;

import com.codecool.stockapp.model.Currencies.CryptoCurrency;
import com.codecool.stockapp.model.Order.Order;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Trader {

    @Autowired
    CurrencyAPIService currencyAPIService;

    private Set<Order> orders = new HashSet<>();

    public Trader() {
        orders.add(new Order("BTC", 1, 9000));
        orders.add(new Order("ETH", 1, 300));
    }

    public void buy(Order order) {
        orders.add(order);
    }

    public void sell(Order order) {
        orders.remove(order);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public CryptoCurrency getCurrencies() {
        return currencyAPIService.getCurrencies();
    }

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {
        return currencyAPIService.getSortedCurrencies(sortBy, sortDir);
    }
}
