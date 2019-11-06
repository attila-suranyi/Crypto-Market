package com.codecool.stockapp.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private String symbol;
    private double boughtPrice;
    private String date;
    private double quantity;

    public Order(String symbol, double quantity, double boughtPrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.boughtPrice = boughtPrice;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
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
