package com.example.likelionspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }
}
