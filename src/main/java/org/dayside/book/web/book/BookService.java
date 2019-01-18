package org.dayside.book.web.book;

import org.dayside.book.constant.NaverBookApiProperties;
import org.dayside.book.web.book.model.BookApiResultModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
    private final String bookSearchUrl = "https://openapi.naver.com/v1/search/book.json";
    private final HttpEntity<BookApiResultModel> naverBookApiHttpEntity;

    public BookService(NaverBookApiProperties naverBookApiProperties) {
        HttpHeaders naverBookApiHttpHeaders= new HttpHeaders();
        naverBookApiHttpHeaders.add("X-Naver-Client-Id", naverBookApiProperties.getClientId());
        naverBookApiHttpHeaders.add("X-Naver-Client-Secret", naverBookApiProperties.getClientSecret());

        naverBookApiHttpEntity = new HttpEntity<>(naverBookApiHttpHeaders);
    }

    public ResponseEntity<BookApiResultModel> getBooks(String keyword, int page) {
        String query = "?display=12&sort=sim&query=" + keyword + "&start=" + page;
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(bookSearchUrl + query, HttpMethod.GET,
                naverBookApiHttpEntity, BookApiResultModel.class);
    }
}
