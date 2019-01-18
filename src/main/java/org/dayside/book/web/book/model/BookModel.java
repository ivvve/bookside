package org.dayside.book.web.book.model;

import lombok.Data;

@Data
public class BookModel {
    private String title;
    private String link;
    private String image;
    private String author;
    private int price;
    private int discount;
    private String publisher;
    private String pubdate;
    private String isbn;
    private String description;
}