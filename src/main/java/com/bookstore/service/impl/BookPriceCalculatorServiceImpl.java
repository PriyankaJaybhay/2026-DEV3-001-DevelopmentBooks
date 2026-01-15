package com.bookstore.service.impl;

import com.bookstore.model.Basket;
import com.bookstore.model.Book;
import com.bookstore.service.BookPriceCalculatorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookPriceCalculatorServiceImpl implements BookPriceCalculatorService {

    private static final double BOOK_PRICE = 50.0;

    @Override
    public double calculatePrice(Basket basket) {
        if (basket == null || basket.getBooks().isEmpty()) {
            return 0.0;
        } else if (basket.getBooks().size() == 1) {
            return BOOK_PRICE;
        }
        return findBestPriceForTheBasket(basket);
    }

    private double findBestPriceForTheBasket(Basket basket) {
        Set<Book> uniqueBooks = new HashSet<>(basket.getBooks());

        /*To handle case of 2 unique books*/
        if (uniqueBooks.size() == 2) {
            return basket.getBooks().size() * BOOK_PRICE * 0.95; /*Discount 5% = 1 - 0.05 = 0.95*/
        }

        /*To handle case of 3 unique books*/
        if (uniqueBooks.size() == 3) {
            return basket.getBooks().size() * BOOK_PRICE * 0.90; /*Discount 10% = 1 - 0.10 = 0.90*/
        }

        /*To handle case of 4 unique books*/
        if (uniqueBooks.size() == 4) {
            return basket.getBooks().size() * BOOK_PRICE * 0.80; /*Discount 20% = 1 - 0.20 = 0.80*/
        }
        
        /*To handle case of 5 unique books*/
        if (uniqueBooks.size() == 5) {
            return basket.getBooks().size() * BOOK_PRICE * 0.75; /*Discount 20% = 1 - 0.25 = 0.75*/
        }
        return basket.getBooks().size() * BOOK_PRICE;
    }
}
