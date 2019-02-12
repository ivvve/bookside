package org.dayside.book.web.service.impl;

import org.dayside.book.web.dto.CommonBookDto;
import org.dayside.book.web.dto.KakaoBookSearchResponseDto;
import org.dayside.book.web.service.BookSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@EnableCaching
@Service
public class KakaoBookSearchServiceImpl implements BookSearchService {
    private static final String API_URL = "https://dapi.kakao.com/v3/search/book";
    private final HttpEntity<String> httpEntity;
    private static final Page emptyPage = new PageImpl(Collections.emptyList(), PageRequest.of(0, 1), 0);

    public KakaoBookSearchServiceImpl(@Value("${kakao.book-search.key}") String apiKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", apiKey);
        httpEntity = new HttpEntity<>(httpHeaders);
    }

    @Cacheable(cacheNames = "bookSearchCache", key = "#keyword.concat('-').concat(#target).concat('-').concat(#page)")
    @Override
    public Page<CommonBookDto> searchBooks(String keyword, String target, int page) {
        String apiRequestUri = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("sort", "accuracy")
                .queryParam("size", BookSearchService.PAGE_SIZE)
                .queryParam("page", page)
                .queryParam("query", keyword)
                .queryParam("target", target)
                .build().toUriString();

        // TODO RestTemplate new 말고 HttpComponentsClientHttpRequestFactory를 통해 생성하기
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<KakaoBookSearchResponseDto> bookSearchResponse =
                restTemplate.exchange(apiRequestUri, HttpMethod.GET, httpEntity, KakaoBookSearchResponseDto.class);

        if (HttpStatus.OK == bookSearchResponse.getStatusCode()) {
            page = (page > 0 ? page - 1 : page);
            return bookSearchResponse.getBody().toPage(page);
        }

        return emptyPage;
    }

    @Override
    public Page<CommonBookDto> searchBooks(String keyword, int page) {
        return this.searchBooks(keyword, DEFAULT_TARGET, page);
    }

}
