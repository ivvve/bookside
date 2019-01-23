package org.dayside.book.domain;

import org.dayside.book.domain.enums.BookOrderStatus;
import org.dayside.book.domain.enums.MemberStatus;
import org.dayside.book.repository.BookOrderRepository;
import org.dayside.book.repository.BookRepository;
import org.dayside.book.repository.MemberRepository;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DomainTests {
    @Autowired MemberRepository memberRepository;
    @Autowired BookOrderRepository bookOrderRepository;
    @Autowired BookRepository bookRepository;

    private final String TESTER_EMAIL = "tester@gmail.com";
    private final String TESTER_PASSWORD = "test1234!@#$";

    private final String BOOK_TITLE = "토비의 스프링 3.1 세트";
    private final String BOOK_ISBN = "9788960773431";
    private final int BOOK_PRICE = 67500;
    private final String ORDER_ADDRESS = "서울특별시";

    @Test(expected = DataIntegrityViolationException.class)
    public void 주문자가_없는_경우_주문_저장_오류_테스트() {
        BookEntity book = BookEntity.builder()
                .isbn(BOOK_ISBN)
                .title(BOOK_TITLE)
                .price(BOOK_PRICE)
                .build();

        bookRepository.save(book);

        BookOrderEntity bookOrder = BookOrderEntity.builder()
                .orderStatus(BookOrderStatus.REQUEST)
                .address(ORDER_ADDRESS)
                .orderDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        bookOrder.addBook(book);

        bookOrderRepository.save(bookOrder);
    }
}
