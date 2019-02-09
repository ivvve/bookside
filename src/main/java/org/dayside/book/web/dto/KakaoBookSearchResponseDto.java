package org.dayside.book.web.dto;

import lombok.Data;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Data
public class KakaoBookSearchResponseDto {
    private Meta meta;
    private List<Book> documents;

    @Data
    private static class Book {
        private String title;
        private List<String> authors;
        private String publisher;
        private String thumbnail;
        private String isbn;
        private int price;
        private int sale_price;
        private String contents;

        private CommonBookDto toCommonBookDto() {
            String author = authors.stream().collect(Collectors.joining(", "));

            return CommonBookDto.builder()
                    .title(this.title)
                    .author(author)
                    .publisher(this.publisher)
                    .imageUrl(this.thumbnail)
                    .isbn(this.isbn)
                    .price(this.price)
                    .discountedPrice(this.sale_price)
                    .description(this.contents)
                    .build();
        }
    }

    @Data
    private static class Meta {
         private boolean is_end;
         private long pageable_count;
         private long total_count;
    }

}

