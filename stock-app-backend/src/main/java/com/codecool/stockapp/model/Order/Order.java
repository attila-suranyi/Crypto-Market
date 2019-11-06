package com.codecool.stockapp.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private String symbol;
    private double boughtPrice;
    private String date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");

    public Order(String symbol, double boughtPrice) {
        this.symbol = symbol;
        this.boughtPrice = boughtPrice;
        this.date = dateFormat.format(new Date());

    }

    public String getSymbol() {
        return symbol;
    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public String getDate() {
        return date;
    }
}
