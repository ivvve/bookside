package org.dayside.book.web.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class NaverBookApiSearchResultDto {
    private long total;
    private int start;
    private int display;
    private List<BookSearchDto> items;
}
