package org.dayside.book.domain;

import org.dayside.book.domain.enums.BookOrderStatus;
import org.dayside.book.domain.enums.MemberStatus;
import org.dayside.book.repository.BookOrderRepository;
import org.dayside.book.repository.MemberRepository;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DomainTests {
    @Autowired MemberRepository memberRepository;
    @Autowired BookOrderRepository bookOrderRepository;

    private final String TESTER_EMAIL = "tester@gmail.com";
    private final String TESTER_PASSWORD = "test1234!@#$";

    private final String BOOK_TITLE = "토비의 스프링 3.1 세트";
    private final String BOOK_ISBN = "9788960773431";
    private final int BOOK_PRICE = 67500;
    private final String ORDER_ADDRESS = "서울특별시";

    @Autowired
    EntityManager entityManager;
    @Test
    public void 회원_책주문_매핑_테스트() {
        Session session = entityManager.unwrap(Session.class);

        MemberEntity member = MemberEntity.builder()
                .email(TESTER_EMAIL)
                .password(TESTER_PASSWORD)
                .memberStatus(MemberStatus.ADMIN)
                .build();

        BookOrderEntity bookOrder = BookOrderEntity.builder()
                .book(new Book(BOOK_TITLE, BOOK_ISBN, BOOK_PRICE))
                .address(ORDER_ADDRESS)
                .orderDate(LocalDateTime.now())
                .orderStatus(BookOrderStatus.REQUEST)
                .orderer(member)
                .build();

        member.addBookOrder(bookOrder);


        memberRepository.save(member);
        bookOrderRepository.save(bookOrder);

        session.flush();

        member = memberRepository.getOne(member.getId());
        bookOrder = bookOrderRepository.getOne(bookOrder.getId());

        assertThat(bookOrder.getOrderer(), is(member));
        assertThat(member.getBookOrderSet(), contains(bookOrder));
    }

    @Test(expected = RuntimeException.class)
    public void 주문자가_없는_경우_주문_저장_오류_테스트() {
        bookOrderRepository.save(BookOrderEntity.builder()
                .book(new Book(BOOK_TITLE, BOOK_ISBN, BOOK_PRICE))
                .address(ORDER_ADDRESS)
                .build());
    }
}
