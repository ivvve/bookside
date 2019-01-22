package org.dayside.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dayside.book.domain.enums.BookOrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "BOOL_ORDER")
public class BookOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Book book;
    @Column(nullable = false)
    private String address; // 배송지
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private BookOrderStatus orderStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERER_ID", nullable = false)
    private MemberEntity orderer; // 주문자

    @Builder
    public BookOrderEntity(Book book, String address, LocalDateTime orderDate, BookOrderStatus orderStatus, MemberEntity orderer) {
        this.book = book;
        this.address = address;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderer = orderer;
    }

    public void setOrderer(MemberEntity orderer) {
        this.orderer = orderer;
    }
}
