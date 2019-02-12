package org.dayside.book.web.controller;

import org.dayside.book.web.dto.CommonBookDto;
import org.dayside.book.web.service.BookSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
}