package com.example.likelionspringboot.domain.article.article.controller;

import com.example.likelionspringboot.domain.article.article.entity.Article;
import com.example.likelionspringboot.domain.article.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ArticleService articleService;


    // GET /article/list
    @Test
    @DisplayName("게시글 목록 페이지")
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/article/list"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(ArticleController.class))
                .andExpect(handler().methodName("showList"))
                .andExpect(content().string(containsString("""
                        게시글 목록
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        3번 : 제목3
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        2번 : 제목2
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        1번 : 제목1
                        """.stripIndent().trim())));
    }

    // GET /article/detail/{id}
    @Test
    @DisplayName("게시글 내용 페이지")
    void t2() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/article/detail/1"))
                .andDo(print());

        Article article = articleService.findById(1L).get();

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(ArticleController.class))
                .andExpect(handler().methodName("showDetail"))
                .andExpect(content().string(containsString("""
                        게시글 내용
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        <div class="badge badge-outline">1</div>
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString(article.getTitle())))
                .andExpect(content().string(containsString(article.getBody())));
    }

    // GET /article/write
    @Test
    @DisplayName("게시글 작성 페이지")
    @WithUserDetails("user1")
    void t3() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/article/write"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(ArticleController.class))
                .andExpect(handler().methodName("showWrite"))
                .andExpect(content().string(containsString("""
                        게시글 작성
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        <input type="text" name="title"
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        <textarea name="body"
                        """.stripIndent().trim())));
    }

    // POST /article/write
    @Test
    @DisplayName("게시글 작성")
    @WithUserDetails("user1")
    void t4() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(
                        post("/article/write")
                                .with(csrf())
                                .param("title", "제목 new")
                                .param("body", "내용 new")
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().handlerType(ArticleController.class))
                .andExpect(handler().methodName("write"))
                .andExpect(redirectedUrlPattern("/article/list?message=**"));

        Article article = articleService.findLatest().get();

        assertThat(article.getTitle()).isEqualTo("제목 new");
        assertThat(article.getBody()).isEqualTo("내용 new");
    }

    // GET /article/modify/{id}
    // PUT /article/modify/{id}
    // DELETE /article/delete/{id}
}
