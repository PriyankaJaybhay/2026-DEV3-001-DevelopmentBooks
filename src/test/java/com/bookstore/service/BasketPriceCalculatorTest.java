package com.bookstore.service;

import com.bookstore.model.Basket;
import com.bookstore.model.Book;
import com.bookstore.service.impl.BookPriceCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketPriceCalculatorTest {

    private BookPriceCalculatorServiceImpl bookPriceService;

    @BeforeEach
    void setUp() {
        bookPriceService = new BookPriceCalculatorServiceImpl();
    }

    @Test
    void testEmptyBasket() {
        Basket basket = new Basket(List.of());
        assertEquals(0.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testSingleBook() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE));
        assertEquals(50.0, bookPriceService.calculatePrice(basket));
    }
}
