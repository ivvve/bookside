package org.dayside.book.web.book;

import org.dayside.book.BookApplication;
import org.dayside.book.constant.NaverBookApiProperties;
import org.dayside.book.web.book.model.BookNaverApiSearchResultModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BookApplication.class}) // TODO 통합테스트로 properties를 가져오기엔 너무 비효율적인데...
public class NaverApiTests {
    @Autowired
    NaverBookApiProperties naverBookApiProperties;
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<BookNaverApiSearchResultModel> naverBookApiHttpEntity;
    final String SPRINGBOOT_BOOK_SEARCH_URI = "https://openapi.naver.com/v1/search/book.json?display=12&sort=sim&query=스프링부트&start=1";
    final String TOBYSPRING_DETAIL_SEARCH_URI = "https://openapi.naver.com/v1/search/book_adv.xml?d_isbn=8960773433";

    @Before
    public void 헤더_setting() {
        HttpHeaders naverBookApiHttpHeaders= new HttpHeaders();
        naverBookApiHttpHeaders.add("X-Naver-Client-Id", naverBookApiProperties.getClientId());
        naverBookApiHttpHeaders.add("X-Naver-Client-Secret", naverBookApiProperties.getClientSecret());

        naverBookApiHttpEntity = new HttpEntity<>(naverBookApiHttpHeaders);
    }

    @Test
    public void 책_목록_검색() {
        ResponseEntity<BookNaverApiSearchResultModel> naverBookApiResultEntity =
                restTemplate.exchange(SPRINGBOOT_BOOK_SEARCH_URI, HttpMethod.GET,
                        naverBookApiHttpEntity, BookNaverApiSearchResultModel.class);

        assertThat(naverBookApiResultEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(naverBookApiResultEntity.getBody().getItems(), is(not(empty())));
    }


}
