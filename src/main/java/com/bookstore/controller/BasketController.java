package com.bookstore.controller;

import com.bookstore.dto.BasketRequest;
import com.bookstore.dto.PriceResponse;
import com.bookstore.model.Basket;
import com.bookstore.service.BookPriceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
