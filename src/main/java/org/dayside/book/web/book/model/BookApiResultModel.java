package org.dayside.book.web.book.model;

import lombok.Data;

import java.util.List;

@Data
public class BookApiResultModel {
    private long total;
    private int start;
    private int display;
    private List<BookModel> items;
}
