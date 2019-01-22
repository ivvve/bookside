package org.dayside.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dayside.book.domain.enums.MemberStatus;

import javax.persistence.*;

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
    @Column(name = "status", nullable = false)
    private MemberStatus memberStatus;

    @Builder
    public MemberEntity(String email, String password, MemberStatus memberStatus) {
        this.email = email;
        this.password = password;
        this.memberStatus = memberStatus;
    }
}
