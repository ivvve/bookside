package org.dayside.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dayside.book.domain.enums.MemberStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity(name = "MEMBER")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "orderer", fetch = FetchType.LAZY)
    private Set<BookOrderEntity> bookOrderSet = new HashSet<>();

    @Builder
    private MemberEntity(String email, String password, MemberStatus memberStatus) {
        this.email = email;
        this.password = password;
        this.memberStatus = memberStatus;
    }

    public void addBookOrder(BookOrderEntity bookOrder) {
        this.bookOrderSet.add(bookOrder);
        bookOrder.setOrderer(this);
    }
}
