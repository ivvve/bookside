package org.dayside.book.web.book;

import org.apache.commons.lang3.StringUtils;
import org.dayside.book.web.book.model.NaverBookApiSearchResultModel;
import org.dayside.book.web.book.model.BookSearchModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String bookSearchView(String keyword, @RequestParam(defaultValue = "1") int page,
                           Model model) {
        if (StringUtils.isBlank(keyword)) {
            return "book-search";
        }

        ResponseEntity<NaverBookApiSearchResultModel> booksResult = bookService.getBooks(keyword, page);

        if (booksResult.getStatusCode() != HttpStatus.OK) {
            return "error"; // TODO error page 만들기
        }

        List<BookSearchModel> bookList = booksResult.getBody().getItems();

        if (bookList != null && !bookList.isEmpty()) {
            model.addAttribute("bookList", bookList);
        }

        return "book-search";
    }
}
