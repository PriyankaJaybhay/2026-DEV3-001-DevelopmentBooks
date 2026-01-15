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

        List<Integer> groupOfDifferentBooksSize = new ArrayList<>();
        List<Integer> listOfEachBookCount = new ArrayList<>(bookCountMap.values());

        while (!listOfEachBookCount.isEmpty()) {
            //Collect size of the list every time
            groupOfDifferentBooksSize.add(listOfEachBookCount.size());

            // Decrement all counts of each book as 1 set of different books is already added in groupOfDifferentBooksSize
            for (int i = 0; i < listOfEachBookCount.size(); i++) {
                listOfEachBookCount.set(i, listOfEachBookCount.get(i) - 1);
            }
            // Remove all that reached zero so that we can have different books in next iteration
            listOfEachBookCount.removeIf(count -> count == 0);
        }

        //optimize groups of different books to get best discount based on group size
        optimizeGroupOfDifferentBooksSize(groupOfDifferentBooksSize);

        double totalBasketPrice = 0.0;
        for (int groupSize : groupOfDifferentBooksSize) {
            totalBasketPrice += groupSize * BOOK_PRICE * getDiscountFactor(groupSize);
        }
        return totalBasketPrice;
    }

    private void optimizeGroupOfDifferentBooksSize(List<Integer> groupOfDifferentBooksSize) {
        int countOfFiveBooks = 0;
        int countOfThreeBooks = 0;

        //loop on groupOfDifferentBooksSize and find the number of set of 5 books and 3 books
        for (int size : groupOfDifferentBooksSize) {
            if (size == 5) {
                countOfFiveBooks++;
            }
            if (size == 3) {
                countOfThreeBooks++;
            }
        }
        
        int pairsToConvert = Math.min(countOfFiveBooks, countOfThreeBooks);

        if (pairsToConvert > 0) {
            //remove set of 5 and 3 books from the list
            for (int i = 0; i < pairsToConvert; i++) {
                groupOfDifferentBooksSize.remove(Integer.valueOf(5));
                groupOfDifferentBooksSize.remove(Integer.valueOf(3));
            }

            //add set of 4 books for the removed set of 5 and 3 books
            for (int i = 0; i < pairsToConvert * 2; i++) {
                groupOfDifferentBooksSize.add(4);
            }
        }
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
