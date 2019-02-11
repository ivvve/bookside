package org.dayside.book.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.dayside.book.web.dto.CommonBookDto;
import org.dayside.book.web.service.BookSearchService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookSearchController {

    private final BookSearchService bookSearchService;

    public BookSearchController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }

    @GetMapping("/search")
    public String searchView(String keyword, String target, @RequestParam(defaultValue = "0") int page,
                             Model model) {
        if (!StringUtils.isBlank(keyword) && page > 0) {
            Page<CommonBookDto> bookList = bookSearchService.searchBooks(keyword, target, page);
            model.addAttribute("bookList", bookList);
        }

        return "search/search";
    }

}
