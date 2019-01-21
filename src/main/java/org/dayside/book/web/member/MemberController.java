package org.dayside.book.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login/validate")
    public String loginValidate() {
        // TODO 사용자 체크 기능 추가
        return "book-search";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }
}
