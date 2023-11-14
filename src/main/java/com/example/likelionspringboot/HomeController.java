package com.example.likelionspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    String showMain() {
        return "main";
    }

    @GetMapping("/about")
    @ResponseBody
    String shwoAbout() {
        return "about";
    }
}
