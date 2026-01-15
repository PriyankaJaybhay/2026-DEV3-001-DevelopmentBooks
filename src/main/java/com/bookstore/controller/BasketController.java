package com.bookstore.controller;

import com.bookstore.dto.BasketRequest;
import com.bookstore.dto.PriceResponse;
import com.bookstore.model.Basket;
import com.bookstore.service.BookPriceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    @Autowired
    private BookPriceCalculatorService bookPriceCalculatorService;

    @PostMapping("/calculate-price")
    public ResponseEntity<PriceResponse> calculateBasketPrice(@RequestBody BasketRequest request) {
        Basket basket = new Basket(request.getBooks());
        double price = bookPriceCalculatorService.calculatePrice(basket);
        return ResponseEntity.ok(new PriceResponse(price));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidBookName(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid book name, Please provide valid names from the below list");
        error.put("message", "Valid book codes: CLEAN_CODE, THE_CLEAN_CODER, CLEAN_ARCHITECTURE, TDD_BY_EXAMPLE, LEGACY_CODE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
