package org.dayside.book.web.book;

import org.dayside.book.BookApplication;
import org.dayside.book.constant.NaverBookApiProperties;
import org.dayside.book.web.book.model.NaverBookApiDetailResultModel;
import org.dayside.book.web.book.model.NaverBookApiSearchResultModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Naver 책 검색 API 자체를 테스트 : Naver API의 경우 나중에 변경 될 수 있는 여지가 있기 때문에
 */
@RunWith(SpringRunner.class)
public class NaverApiTests {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<NaverBookApiSearchResultModel> naverBookApiHttpEntity;
    final String SPRINGBOOT_BOOK_SEARCH_URI = "https://openapi.naver.com/v1/search/book.json?display=12&sort=sim&query=스프링부트&start=1";
    final String TOBYSPRING_DETAIL_SEARCH_URI = "https://openapi.naver.com/v1/search/book_adv.xml?d_isbn=8960773433";

    @Before
    public void 헤더_setting() {
        HttpHeaders naverBookApiHttpHeaders= new HttpHeaders();
        naverBookApiHttpHeaders.add("X-Naver-Client-Id", "clientid");
        naverBookApiHttpHeaders.add("X-Naver-Client-Secret", "clientsecet");

        naverBookApiHttpEntity = new HttpEntity<>(naverBookApiHttpHeaders);
    }

    @Test
    public void 책_목록_검색_테스트() {
        ResponseEntity<NaverBookApiSearchResultModel> naverBookApiSearchResultEntity =
                restTemplate.exchange(SPRINGBOOT_BOOK_SEARCH_URI, HttpMethod.GET,
                        naverBookApiHttpEntity, NaverBookApiSearchResultModel.class);

        assertThat(naverBookApiSearchResultEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(naverBookApiSearchResultEntity.getBody().getItems(), is(not(empty())));
    }

    @Test
    public void 책_상세_검색_테스트() {
        ResponseEntity<NaverBookApiDetailResultModel> naverBookApiDetailResultEntity =
                restTemplate.exchange(TOBYSPRING_DETAIL_SEARCH_URI, HttpMethod.GET,
                        naverBookApiHttpEntity, NaverBookApiDetailResultModel.class);

        assertThat(naverBookApiDetailResultEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(naverBookApiDetailResultEntity.getBody().getBookDeatail(), is(not(nullValue())));
    }
}
