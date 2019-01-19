package org.dayside.book.web.book;

import org.dayside.book.constant.NaverBookApiProperties;
import org.dayside.book.web.book.model.NaverBookApiSearchResultModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
    private static final String BOOK_SEARCH_URL = "https://openapi.naver.com/v1/search/book.json";
    private final HttpEntity<NaverBookApiSearchResultModel> naverBookApiHttpEntity;

    public BookService(NaverBookApiProperties naverBookApiProperties) {
        HttpHeaders naverBookApiHttpHeaders= new HttpHeaders();
        naverBookApiHttpHeaders.add("X-Naver-Client-Id", naverBookApiProperties.getClientId());
        naverBookApiHttpHeaders.add("X-Naver-Client-Secret", naverBookApiProperties.getClientSecret());

        naverBookApiHttpEntity = new HttpEntity<>(naverBookApiHttpHeaders);
    }

    public ResponseEntity<NaverBookApiSearchResultModel> getBooks(String keyword, int page) {
        String query = "?display=12&sort=sim&query=" + keyword + "&start=" + page;
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(BOOK_SEARCH_URL + query, HttpMethod.GET,
                naverBookApiHttpEntity, NaverBookApiSearchResultModel.class);
    }
}
