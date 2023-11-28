package com.example.likelionspringboot.domain.home.home.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AdminHomeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("관리자가 아니라면 관리자 홈 페이지 접근 불가")
    @WithUserDetails("user1")
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/admin"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("관리자 홈 페이지")
    @WithUserDetails("admin")
    void t2() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/admin"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AdminHomeController.class))
                .andExpect(handler().methodName("showMain"))
                .andExpect(view().name("home/home/admin/main"));
    }

    @Test
    @DisplayName("관리자 정보 페이지")
    @WithUserDetails("admin")
    void t3() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/admin/home/about"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AdminHomeController.class))
                .andExpect(handler().methodName("showAbout"))
                .andExpect(view().name("home/home/admin/about"));
    }
}