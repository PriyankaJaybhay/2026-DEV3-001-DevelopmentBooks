package com.bookstore.service.impl;

import com.bookstore.model.Basket;
import com.bookstore.model.Book;
import com.bookstore.service.BookPriceCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class BookPriceCalculatorServiceImpl implements BookPriceCalculatorService {

    private static final double BOOK_PRICE = 50.0;

    @Override
    public double calculatePrice(Basket basket) {

        if (basket == null || basket.getBooks().isEmpty()) {
            return 0.0;
        }else if(basket.getBooks().size() == 1){
            return BOOK_PRICE;
        }
        return findBestPriceForTheBasket(basket);
    }

    private double findBestPriceForTheBasket(Basket basket){
        double totalPrice = 0.0;
        if(!basket.getBooks().isEmpty()){
            for(Book book : basket.getBooks()){

            }
        }
        return totalPrice;
    }
}
