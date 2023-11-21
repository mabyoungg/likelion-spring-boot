package com.example.likelionspringboot.global.interceptor;

import com.example.likelionspringboot.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NeedToAdminInterceptor implements HandlerInterceptor {
    private final Rq rq;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!rq.isLogined()) {
            throw new RuntimeException("로그인 후 이용해주세요.");
        }

        List<String> authorities = rq.getSessionAttribute("authorities");

        if (!authorities.contains("ROLE_ADMIN")) {
            throw new RuntimeException("관리자만 이용할 수 있는 페이지 입니다.");
        }
        return true;
    }
}