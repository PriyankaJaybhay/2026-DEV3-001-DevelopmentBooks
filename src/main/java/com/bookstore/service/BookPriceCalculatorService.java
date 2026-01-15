package com.bookstore.service;

import com.bookstore.model.Basket;

public interface BookPriceCalculatorService {
    
    double calculatePrice(Basket basket);
}
