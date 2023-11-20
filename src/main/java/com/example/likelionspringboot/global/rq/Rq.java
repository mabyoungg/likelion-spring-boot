package com.example.likelionspringboot.global.rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequestScope
@Component
@Getter
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public Rq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String redirect(String path, String message) {
        message = URLEncoder.encode(message, StandardCharsets.UTF_8);

        return "redirect:" + path + "?message=" + message;
    }
}
