package org.dayside.book.web.controller;

import org.dayside.book.web.dto.CommonBookDto;
import org.dayside.book.web.service.BookSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookSearchController.class)
public class BookSearchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookSearchService bookSearchService;

    @Test
    public void BookSearchController_view_테스트() throws Exception {
        mockMvc.perform(get("/search"))
                .andDo(print())
                .andExpect(view().name("search/search"));
    }

    @Test
    public void BookSearchController_model_테스트() throws Exception {
        CommonBookDto spring3 = CommonBookDto.builder().title("토비의 스프링 3").build();
        CommonBookDto spring31 = CommonBookDto.builder().title("토비의 스프링 3.1").build();

        when(bookSearchService.searchBooks("토비의 스프링", "title", 1))
                .thenReturn(new PageImpl<>(Arrays.asList(spring3, spring31)));

        mockMvc.perform(get("/search")
                .param("keyword", "토비의 스프링")
                .param("target", "title")
                .param("page", "1"))
                .andDo(print())
                .andExpect(view().name("search/search"))
                .andExpect(model().attributeExists("bookList"));
    }

    @Test
    public void API_호출_오류의_경우_view_테스트() throws Exception {
        when(bookSearchService.searchBooks("헬로 월드", "title", 1))
                .thenReturn(new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 1), 0));

        mockMvc.perform(get("/search")
                .param("keyword", "헬로 월드")
                .param("target", "title")
                .param("page", "1"))
                .andDo(print())
                .andExpect(view().name("search/search"))
                .andExpect(content().string(containsString("서버에 문제가 생겼습니다.")))
                .andExpect(content().string(containsString("다시 시도해주세요.")));
    }

    @Test
    public void 책_검색_결과_없는_경우_view_테스트() throws Exception {
        when(bookSearchService.searchBooks("헬로 월드", "title", 1))
                .thenReturn(new PageImpl<>(Collections.emptyList(), PageRequest.of(1, 12), 0));

        mockMvc.perform(get("/search")
                .param("keyword", "헬로 월드")
                .param("target", "title")
                .param("page", "1"))
                .andDo(print())
                .andExpect(view().name("search/search"))
                .andExpect(content().string(containsString("검색 결과가 없습니다.")));
    }
}