package org.dayside.book.web.member.dto;

import lombok.Data;
import org.dayside.book.domain.MemberEntity;
import org.dayside.book.domain.enums.MemberStatus;

@Data
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private MemberStatus memberStatus;

    private MemberEntity toEntity() {
        return MemberEntity.builder()
                .email(this.email)
                .password(this.password)
                .memberStatus(this.memberStatus)
                .build();
    }
}
