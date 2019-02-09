package org.dayside.book.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonBookDto {
    private String title;
    private String author;
    private String publisher;
    private String imageUrl;
    private String isbn;
    private int price;
    private int discountedPrice;
    private String description;
}
