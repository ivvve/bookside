package org.dayside.book.web.book.model;

import lombok.Data;

import java.util.List;

@Data
public class NaverBookApiSearchResultModel {
    private long total;
    private int start;
    private int display;
    private List<BookSearchModel> items;
}
