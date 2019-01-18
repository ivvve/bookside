package org.dayside.book.web.book;

import org.dayside.book.web.book.model.BookApiResultModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookApiResultModel> getBooks(@RequestParam String keyword, @RequestParam(defaultValue = "1") int page) {
        return bookService.getBooks(keyword, page);
    }
}
