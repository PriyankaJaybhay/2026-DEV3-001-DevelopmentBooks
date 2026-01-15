package com.bookstore.dto;

import com.bookstore.model.Book;
import java.util.List;

public class BasketRequest {
    private List<Book> books;

    public BasketRequest() {
    }

    public BasketRequest(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
