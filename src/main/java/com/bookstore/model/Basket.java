package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Book> books;

    public Basket(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

}
