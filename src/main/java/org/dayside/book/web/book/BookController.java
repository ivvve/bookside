package org.dayside.book.web.book;

import org.apache.commons.lang3.StringUtils;
import org.dayside.book.web.book.model.NaverBookApiSearchResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String bookSearchView(String keyword, Integer page, Model model) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(keyword)) {
            return "book-search";
        }

        if (page == null) {
            keyword = URLEncoder.encode(keyword, "UTF-8");
            return "redirect:/book/search?keyword=" + keyword + "&page=" + 1;
        }

        ResponseEntity<NaverBookApiSearchResultModel> booksResult = bookService.getBooks(keyword, page);

        if (booksResult.getStatusCode() != HttpStatus.OK) {
            return "error"; // TODO error page 만들기
        }

        model.addAttribute("bookSearchResult", booksResult.getBody());

        return "book-search";
    }
}
