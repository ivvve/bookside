package org.dayside.book.web.book;

import org.dayside.book.common.constant.NaverBookApiProperties;
import org.dayside.book.web.book.dto.NaverBookApiDetailResultDto;
import org.dayside.book.web.book.dto.NaverBookApiSearchResultDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
    private static final String BOOK_SEARCH_URL = "https://openapi.naver.com/v1/search/book.json";
    private static final String BOOK_DETAIL_URL = "https://openapi.naver.com/v1/search/book_adv.xml";

    private final HttpEntity<NaverBookApiSearchResultDto> naverBookApiHttpEntity;

    public BookService(NaverBookApiProperties naverBookApiProperties) {
        HttpHeaders naverBookApiHttpHeaders= new HttpHeaders();
        naverBookApiHttpHeaders.add("X-Naver-Client-Id", naverBookApiProperties.getClientId());
        naverBookApiHttpHeaders.add("X-Naver-Client-Secret", naverBookApiProperties.getClientSecret());

        naverBookApiHttpEntity = new HttpEntity<>(naverBookApiHttpHeaders);
    }

    public ResponseEntity<NaverBookApiSearchResultDto> searchBooks(String keyword, int page) {
        int start = (page - 1) * 12 + 1;
        String query = "?display=12&sort=sim&query=" + keyword + "&start=" + start;
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(BOOK_SEARCH_URL + query, HttpMethod.GET,
                naverBookApiHttpEntity, NaverBookApiSearchResultDto.class);
    }

    public ResponseEntity<NaverBookApiDetailResultDto> getBookDetailByIsbn(String isbn) {
        String query = "?d_isbn=" + isbn;
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(BOOK_DETAIL_URL + query, HttpMethod.GET,
                naverBookApiHttpEntity, NaverBookApiDetailResultDto.class);
    }
}
