package com.example.likelionspringboot.domain.home.home.controller;

import com.example.likelionspringboot.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminHomeController {
    private final Rq rq;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String showMain() {
        return "home/home/admin/main";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home/about")
    public String showAbout() {
        return "home/home/admin/about";
    }
}