package com.example.likelionspringboot.domain.home.home.controller;

import com.example.likelionspringboot.global.rq.Rq;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Rq rq;

    @GetMapping("/")
    public String goToArticleList(String message) {
        return rq.redirect("/article/list", message);
    }

    @GetMapping("/home/session")
    @ResponseBody
    public Map<String, Object> showSession(HttpSession session) {
        return Collections.list(session.getAttributeNames()).stream()
                .collect(
                        Collectors.toMap(
                                key -> key,
                                key -> session.getAttribute(key)
                        )
                );
    }

    @GetMapping("/home/test1")
    @ResponseBody
    public String showTest1() {
        return Thread.currentThread().getName();
    }
}