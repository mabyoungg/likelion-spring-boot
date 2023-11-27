package com.example.likelionspringboot.domain.member.member.controller;

import com.example.likelionspringboot.domain.member.member.entity.Member;
import com.example.likelionspringboot.domain.member.member.service.MemberService;
import com.example.likelionspringboot.global.resultData.ResultData;
import com.example.likelionspringboot.global.rq.Rq;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    String showLogin() {
        return "member/member/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/join")
    String showJoin() {
        return "member/member/join";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    String join(@NotBlank String username,
                @NotBlank String password) {
        ResultData<Member> joinResult = memberService.join(username, password);

        if (joinResult.isFail())
            return rq.historyBack(joinResult);

        return rq.redirect("/member/login", joinResult);
    }
}
