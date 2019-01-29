package org.dayside.book.web.member;

import org.dayside.book.domain.MemberEntity;
import org.dayside.book.repository.MemberRepository;
import org.dayside.book.web.member.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isMemberExist(MemberDto member) {
        // TODO 시큐리티 붙여서 기능 변경할 것
        return memberRepository.findByEmailAndPassword(member.getEmail(), member.getPassword()).isPresent();
    }

    /**
     * isMemberExist를 통해 존재가 확인된 멤버를 가져오는 메서드
     * @param member
     * @return
     */
    public MemberEntity getExistingMember(MemberDto member) {
        return memberRepository.findByEmailAndPassword(member.getEmail(), member.getPassword()).get();
    }

}
