package com.example.likelionspringboot.domain.home.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {
    @GetMapping("/admin")
    public String showMain() {
        return "home/home/admin/main";
    }

    @GetMapping("/admin/home/about")
    public String showAbout() {
        return "home/home/admin/about";
    }
}