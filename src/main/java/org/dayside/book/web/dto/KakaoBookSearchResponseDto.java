package org.dayside.book.web.dto;

import lombok.Data;
import org.dayside.book.web.service.BookSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
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
         private int pageable_count; // 검색 가능한 책의 수
         private int total_count; // 모든 책의 수 (검색 가능한 수는 아님)
    }

    /**
     * Kakao 책 검색 API 응답을 Page<CommonBookDto> 객체로 변환하여 리턴
     * @param page 현재 페이지 (0 부터 시작)
     * @return
     */
    public Page<CommonBookDto> toPage(int page) {
        Pageable pageable = PageRequest.of(page, BookSearchService.PAGE_SIZE);
        List<CommonBookDto> bookList = documents.stream()
                .map(Book::toCommonBookDto)
                .collect(Collectors.toList());

        return new PageImpl(bookList, pageable, meta.pageable_count);
    }
}

