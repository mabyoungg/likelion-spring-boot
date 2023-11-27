package com.example.likelionspringboot.global.rq;

import com.example.likelionspringboot.domain.member.member.entity.Member;
import com.example.likelionspringboot.domain.member.member.service.MemberService;
import com.example.likelionspringboot.global.resultData.ResultData;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RequestScope
@Component
@Getter
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberService memberService;
    private User user;
    private Member member;

    @PostConstruct
    public void init() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            this.user = (User) authentication.getPrincipal();
        }
    }

    public String redirect(String path, ResultData<?> resultData) {
        return redirect(path, resultData.getMessage());
    }

    public String redirect(String path, String message) {
        if (message == null)
            return "redirect:" + path;

        boolean containsTtl = message.contains(";ttl=");

        if (containsTtl) {
            message = message.split(";ttl=", 2)[0];
        }

        message = URLEncoder.encode(message, StandardCharsets.UTF_8);
        message += ";ttl=" + (new Date().getTime() + 1000 * 5);

        return "redirect:" + path + "?message=" + message;
    }

    private String getMemberUsername() {
        return user.getUsername();
    }

    public Member getMember() {
        if (!isLogined()) {
            return null;
        }

        if (member == null)
            member = memberService.findByUsername(getMemberUsername()).get();

        return member;
    }

    public boolean isLogined() {
        return user != null;
    }

    public void setSessionAttribute(String name, Object value) {
        request.getSession().setAttribute(name, value);
    }

    public void removeSessionAttribute(String name) {
        request.getSession().removeAttribute(name);
    }

    public boolean isAdmin() {
        if (!isLogined()) {
            return false;
        }

        return user.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public <T> T getSessionAttribute(String name) {
        return (T) request.getSession().getAttribute(name);
    }

    public String historyBack(ResultData<?> resultData) {
        return historyBack(resultData.getMessage());
    }

    public String historyBack(String message) {
        response.setStatus(400);
        request.setAttribute("message", message);

        return "global/js";
    }

    public String redirectOrBack(String url, ResultData<?> resultData) {
        if (resultData.isFail())
            return historyBack(resultData);

        return redirect(url, resultData);
    }
}
