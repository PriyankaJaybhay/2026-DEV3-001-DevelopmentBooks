package com.bookstore.dto;

public class PriceResponse {
    private double totalPrice;
    private String currency;

    public PriceResponse() {
    }

    public PriceResponse(double totalPrice) {
        this.totalPrice = totalPrice;
        this.currency = "EUR";
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
