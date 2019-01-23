package org.dayside.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "BOOK")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String isbn;
    private int price;

    @Builder
    private BookEntity(String title, String isbn, int price) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
    }
}
