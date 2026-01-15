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

    @Test
    void testTwoDifferentBooks() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_ARCHITECTURE));
        assertEquals(95.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testTwoSameBooks() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_CODE));
        assertEquals(100.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testThreeDifferentBooks() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_ARCHITECTURE, Book.LEGACY_CODE));
        assertEquals(135.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testThreeSameBooks() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_CODE, Book.CLEAN_CODE));
        assertEquals(150.0, bookPriceService.calculatePrice(basket));
    }
}
