package com.bookstore.service.impl;

import com.bookstore.model.Basket;
import com.bookstore.model.Book;
import com.bookstore.service.BookPriceCalculatorService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookPriceCalculatorServiceImpl implements BookPriceCalculatorService {

    private static final double BOOK_PRICE = 50.0;

    @Override
    public double calculatePrice(Basket basket) {
        if (basket == null || basket.getBooks().isEmpty()) {
            return 0.0;
        }
        return findBestPriceForTheBasket(basket);
    }

    private double findBestPriceForTheBasket(Basket basket) {
        Map<Book, Integer> bookCountMap = new HashMap<>();
        for (Book book : basket.getBooks()) {
            if (bookCountMap.containsKey(book)) {
                bookCountMap.put(book, bookCountMap.get(book) + 1);
            } else {
                bookCountMap.put(book, 1);
            }
        }

        double totalPrice = 0.0;

        while (!bookCountMap.isEmpty()) {

            int bookGroupSize = 0;
            List<Book> booksToRemove = new ArrayList<>();

            // Decrease count for each book and track which ones to remove
            for (Map.Entry<Book, Integer> bookKey : bookCountMap.entrySet()) {
                bookGroupSize++;

                int currentCount = bookKey.getValue();
                int newCount = currentCount - 1;

                if (newCount == 0) {
                    booksToRemove.add(bookKey.getKey());
                } else {
                    bookKey.setValue(newCount);
                }
            }

            // Remove books that reached count 0
            for (Book book : booksToRemove) {
                bookCountMap.remove(book);
            }

            // Update total price for this group
            totalPrice += bookGroupSize * BOOK_PRICE * getDiscountFactor(bookGroupSize);
        }
        return totalPrice;
    }

    private double getDiscountFactor(int bookGroupSize) {
        switch (bookGroupSize) {
            case 2:
                return 0.95;
            case 3:
                return 0.90;
            case 4:
                return 0.80;
            case 5:
                return 0.75;
            default:
                return 1.0;
        }
    }
}
