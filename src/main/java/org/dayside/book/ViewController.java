package org.dayside.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping({"/", "index"})
    public String index() {
        return "index";
    }

    @GetMapping("/404")
    public String error404() {
        return "404";
    }

    @GetMapping("blog-archive")
    public String blog_archive() {
        return "blog-archive";
    }

    @GetMapping("blog-sigle")
    public String blog_sigle() {
        return "blog-sigle";
    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/properties")
    public String properties() {
        return "properties";
    }

    @GetMapping("/properties-detail")
    public String properties_detail() {
        return "properties-detail";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

}
