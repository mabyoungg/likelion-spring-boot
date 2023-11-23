package com.example.likelionspringboot.domain.home.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @GetMapping("")
    public String showMain() {
        return "home/home/admin/main";
    }

    @GetMapping("/home/about")
    public String showAbout() {
        return "home/home/admin/about";
    }
}