package org.dayside.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dayside.book.domain.enums.BookOrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "BOOK_ORDER")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERER_ID")
    private Member orderer;

    @Enumerated(EnumType.STRING)
    private BookOrderStatus orderStatus;

    private LocalDateTime orderDate;

    private LocalDateTime processedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BOOK_BOOK_ORDER_JOIN_TABLE",
            joinColumns = @JoinColumn(name = "BOOK_ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    private List<Book> bookList;

    @Builder
    public BookOrder(BookOrderStatus orderStatus, LocalDateTime orderDate, LocalDateTime processedDate) {
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.processedDate = processedDate;
    }
}
