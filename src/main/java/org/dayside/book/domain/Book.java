package org.dayside.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class Book {
    private String title;
    @Column(nullable = false)
    private String isbn;
    private int price;

    @Builder
    public Book(String title, String isbn, int price) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
    }
}
