package org.dayside.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewContoller {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
