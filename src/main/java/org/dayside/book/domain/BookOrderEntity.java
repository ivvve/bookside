package org.dayside.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dayside.book.domain.enums.BookOrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity(name = "BOOK_ORDER")
public class BookOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String address; // 배송지
    private LocalDateTime orderDate; // 주문 날짜
    private LocalDateTime updatedDate; // 상태 변경일

    @Enumerated(EnumType.STRING)
    private BookOrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERER_ID", nullable = false)
    private MemberEntity orderer; // 주문자

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<BookEntity> bookSet = new HashSet<>();

    @Builder
    private BookOrderEntity(String address, LocalDateTime orderDate, LocalDateTime updatedDate, BookOrderStatus orderStatus, MemberEntity orderer) {
        this.address = address;
        this.orderDate = orderDate;
        this.updatedDate = updatedDate;
        this.orderStatus = orderStatus;
        this.orderer = orderer;
    }

    public void setOrderer(MemberEntity orderer) {
        this.orderer = orderer;
    }

    public void addBook(BookEntity book) {
        this.bookSet.add(book);
    }
}
