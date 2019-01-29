package org.dayside.book.web.book;

import org.apache.commons.lang3.StringUtils;
import org.dayside.book.web.book.dto.NaverBookApiDetailResultDto;
import org.dayside.book.web.book.dto.NaverBookApiSearchResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            return "book/search";
        }

        if (page == null) {
            keyword = URLEncoder.encode(keyword, "UTF-8");
            return "redirect:/book/search?keyword=" + keyword + "&page=" + 1;
        }

        ResponseEntity<NaverBookApiSearchResultDto> booksResult = bookService.searchBooks(keyword, page);

        if (booksResult.getStatusCode() != HttpStatus.OK) {
            return "error"; // TODO error page 만들기
        }

        model.addAttribute("bookSearchResult", booksResult.getBody());

        return "book/search";
    }

    @GetMapping("/detail")
    @ResponseBody
    public ResponseEntity<NaverBookApiDetailResultDto> getBookDetailByIsbnAjax(String isbn) {
        if (StringUtils.isBlank(isbn)) {
            return ResponseEntity.notFound().build();
        }

        ResponseEntity<NaverBookApiDetailResultDto> bookDetailResultEntity = bookService.getBookDetailByIsbn(isbn);

        if (bookDetailResultEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(bookDetailResultEntity.getStatusCode()).build();
        }

        return ResponseEntity.ok().body(bookDetailResultEntity.getBody());
    }

    @GetMapping("/order")
    public String bookOrderView() {
        return "book/order";
    }
}
