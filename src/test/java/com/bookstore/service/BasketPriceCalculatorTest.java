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

    @Test
    void testFourDifferentBooks() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_ARCHITECTURE, Book.LEGACY_CODE, Book.TDD_BY_EXAMPLE));
        assertEquals(160.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testFiveDifferentBooks() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_ARCHITECTURE, Book.LEGACY_CODE, Book.TDD_BY_EXAMPLE, Book.THE_CLEAN_CODER));
        assertEquals(187.50, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testTwoSameBooksAndOneDifferent() {
        // 2 Clean Code + 1 Clean Coder
        // Group 1: [CLEAN_CODE, THE_CLEAN_CODER] = 2 * 50 * 0.95 = 95
        // Group 2: [CLEAN_CODE] = 1 * 50 = 50
        // Total: 145
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_CODE, Book.THE_CLEAN_CODER));
        assertEquals(145.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testTwoPairsOfDifferentBooks() {
        // 2 Clean Code + 2 Clean Coder
        // Group 1: [CLEAN_CODE, THE_CLEAN_CODER] = 2 * 50 * 0.95 = 95
        // Group 2: [CLEAN_CODE, THE_CLEAN_CODER] = 2 * 50 * 0.95 = 95
        // Total: 190
        Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_CODE, Book.THE_CLEAN_CODER, Book.THE_CLEAN_CODER));
        assertEquals(190.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testComplexGroupingExample() {
        // 2 Clean Code + 2 Clean Coder + 2 Clean Architecture + 1 TDD + 1 Legacy
        // Optimal grouping: [4, 4] = 4*50*0.80 + 4*50*0.80 = 160 + 160 = 320
        Basket basket = new Basket(List.of(
            Book.CLEAN_CODE, Book.CLEAN_CODE,
            Book.THE_CLEAN_CODER, Book.THE_CLEAN_CODER,
            Book.CLEAN_ARCHITECTURE, Book.CLEAN_ARCHITECTURE,
            Book.TDD_BY_EXAMPLE,
            Book.LEGACY_CODE
        ));
        assertEquals(320.0, bookPriceService.calculatePrice(basket));
    }

    @Test
    void testElevenBooksMixedCounts() {
        // 3 Clean Code + 3 Clean Coder + 2 Clean Architecture + 2 TDD + 1 Legacy = 11 books
        // Optimal grouping: [5, 4, 2] = 187.5 + 160 + 95 = 442.5
        Basket basket = new Basket(List.of(
            Book.CLEAN_CODE, Book.CLEAN_CODE, Book.CLEAN_CODE,
            Book.THE_CLEAN_CODER, Book.THE_CLEAN_CODER, Book.THE_CLEAN_CODER,
            Book.CLEAN_ARCHITECTURE, Book.CLEAN_ARCHITECTURE,
            Book.TDD_BY_EXAMPLE, Book.TDD_BY_EXAMPLE,
            Book.LEGACY_CODE
        ));
        assertEquals(442.5, bookPriceService.calculatePrice(basket));
    }

}
