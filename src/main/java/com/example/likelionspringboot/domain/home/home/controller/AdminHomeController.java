package com.example.likelionspringboot.domain.home.home.controller;

import com.example.likelionspringboot.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminHomeController {
    private final Rq rq;

    @GetMapping("/admin")
    public String showMain() {
        return "home/home/admin/main";
    }

    @GetMapping("/admin/home/about")
    public String showAbout() {
        return "home/home/admin/about";
    }
}