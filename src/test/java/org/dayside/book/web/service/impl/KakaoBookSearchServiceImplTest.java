package org.dayside.book.web.service.impl;

import org.dayside.book.web.dto.CommonBookDto;
import org.dayside.book.web.service.BookSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class KakaoBookSearchServiceImplTest {
    private BookSearchService bookSearchService = new KakaoBookSearchServiceImpl("app key");

    @Test
    public void 책_제목_검색_테스트() {
        Page<CommonBookDto> bookList = bookSearchService.searchBooks("토비의 스프링", "title", 1);

        assertThat(bookList.getContent(), not(nullValue()));
        assertThat(bookList.isFirst(), is(true));

        System.out.println("============검색 결과============");
        bookList.forEach(System.out::println);
        System.out.println("==================================");
    }

    @Test
    public void 책_ISBN_검색_테스트() {
        Page<CommonBookDto> bookList = bookSearchService.searchBooks("9788960773431", "isbn", 1);

        assertThat(bookList.getContent(), not(nullValue()));
        assertThat(bookList.isFirst(), is(true));

        System.out.println("============검색 결과============");
        bookList.forEach(System.out::println);
        System.out.println("==================================");
    }


}