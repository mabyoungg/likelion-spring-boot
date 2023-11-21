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
        if (!rq.isAdmin()) {
            throw new RuntimeException("관리자만 접근할 수 있습니다.");
        }

        return "home/home/admin/main";
    }

    @GetMapping("/admin/home/about")
    public String showAbout() {
        if (!rq.isAdmin()) {
            throw new RuntimeException("관리자만 접근할 수 있습니다.");
        }

        return "home/home/admin/about";
    }
}