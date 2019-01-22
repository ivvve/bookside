package org.dayside.book.domain.enums;

import lombok.Getter;

@Getter
public enum  MemberStatus {
    WAIT, // 가입 대기,
    MEMBER, //가입,
    LEFT, // 탈퇴
    ADMIN // 관리자
}
