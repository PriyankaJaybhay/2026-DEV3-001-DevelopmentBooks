package com.bookstore.model;

public enum Book {
    CLEAN_CODE("Clean Code", "Robert Martin", 2008),
    THE_CLEAN_CODER("The Clean Coder", "Robert Martin", 2011),
    CLEAN_ARCHITECTURE("Clean Architecture", "Robert Martin", 2017),
    TDD_BY_EXAMPLE("Test Driven Development by Example", "Kent Beck", 2003),
    LEGACY_CODE("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);

    private final String title;
    private final String author;
    private final int year;

    Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
