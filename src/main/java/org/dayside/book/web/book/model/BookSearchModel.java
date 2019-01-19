package org.dayside.book.web.book.model;

import lombok.Data;

@Data
public class BookSearchModel {
    private String title;
    private String image;
    private String publisher;
    private String author;
    private int price;
    private int discount;
    private String pubdate;
}
