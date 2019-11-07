package com.codecool.stockapp.model.Order;

public class Order {
    private String symbol;
    private double price;
    private double amount;
    private double total;
    private String date;

    public Order() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "symbol='" + symbol + '\'' +
                ", boughtPrice=" + price +
                ", quantity=" + amount +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
