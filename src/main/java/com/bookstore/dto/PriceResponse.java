package com.bookstore.dto;

public class PriceResponse {
    private double totalPrice;

    public PriceResponse() {
    }

    public PriceResponse(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
