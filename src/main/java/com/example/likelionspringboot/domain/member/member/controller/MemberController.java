package com.example.likelionspringboot.domain.member.member.controller;

import com.example.likelionspringboot.domain.member.member.service.MemberService;
import com.example.likelionspringboot.global.rq.Rq;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/member/login")
    String showLogin() {
        return "member/member/login";
    }

    @Data
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @GetMapping("/member/join")
    String showJoin() {
        return "member/member/join";
    }

    @PostMapping("/member/join")
    String join(@NotBlank String username,
                @NotBlank String password) {
        memberService.join(username, password);

        return rq.redirect("/member/login", "회원가입이 완료되었습니다.");
    }
}
