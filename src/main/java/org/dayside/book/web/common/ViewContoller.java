package org.dayside.book.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewContoller {
    @GetMapping("/")
    public String indexView() {
        return "index";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
}
