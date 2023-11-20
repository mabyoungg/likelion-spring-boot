package com.example.likelionspringboot.global.rq;

import com.example.likelionspringboot.domain.member.member.entity.Member;
import com.example.likelionspringboot.domain.member.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RequestScope
@Component
@Getter
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberService memberService;

    public Rq(HttpServletRequest request, HttpServletResponse response, MemberService memberService) {
        this.request = request;
        this.response = response;
        this.memberService = memberService;
    }

    public String redirect(String path, String message) {
        message = URLEncoder.encode(message, StandardCharsets.UTF_8);

        return "redirect:" + path + "?message=" + message;
    }

    public long getLoginedMemberId() {
        return Optional
                .ofNullable(request.getSession().getAttribute("loginedMemberId"))
                .map(id -> (long) id)
                .orElse(0L);
    }

    public Member getLoginedMember() {
        long loginedMemberId = getLoginedMemberId();

        if (loginedMemberId == 0) {
            return null;
        }

        return memberService.findById(loginedMemberId).get();
    }
}
