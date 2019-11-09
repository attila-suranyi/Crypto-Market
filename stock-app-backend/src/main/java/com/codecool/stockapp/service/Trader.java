package com.codecool.stockapp.service;

import com.codecool.stockapp.model.Currencies.CryptoCurrency;
import com.codecool.stockapp.model.Currencies.DataItem;
import com.codecool.stockapp.model.Order.Order;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class Trader {

    @Autowired
    CurrencyAPIService currencyAPIService;

    private Set<Order> orders = new HashSet<>();

    public Trader() {
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

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {
        return currencyAPIService.getCurrencies(sortBy, sortDir);
    }
}
