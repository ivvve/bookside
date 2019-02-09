package org.dayside.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dayside.book.domain.enums.MemberGrade;
import org.dayside.book.domain.enums.MemberStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String address;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
