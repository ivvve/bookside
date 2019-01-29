package org.dayside.book.web.member;

import org.dayside.book.domain.MemberEntity;
import org.dayside.book.web.member.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginView(HttpServletRequest request) {
        Optional<HttpSession> session = Optional.ofNullable(request.getSession(false));

        MemberEntity loginMember = session
                .map(sezzion -> (MemberEntity) sezzion.getAttribute("loginMember"))
                .orElse(null);

        // 로그인 시 login view 페이지 접속 안되게 하기
        if (loginMember != null) {
            return "redirect:/book/search";
        }

        return "login";
    }

    @PostMapping("/login/validate")
    @ResponseBody
    public ResponseEntity<String> loginValidate(@RequestBody MemberDto member, HttpServletRequest request) {
        if (!memberService.isMemberExist(member)) {
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }

        MemberEntity loginMember = memberService.getExistingMember(member);
        request.getSession().setAttribute("loginMember", loginMember);

        return ResponseEntity.ok().body("{\"redirectUrl\": \"/book/search\"}");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }
}
