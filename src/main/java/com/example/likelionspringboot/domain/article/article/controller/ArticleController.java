package com.example.likelionspringboot.domain.article.article.controller;

import com.example.likelionspringboot.domain.article.article.entity.Article;
import com.example.likelionspringboot.domain.article.article.service.ArticleService;
import com.example.likelionspringboot.global.rq.Rq;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final Rq rq;


    @GetMapping("/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "article/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    String showWrite() {
        return "article/article/write";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    String write(
            @NotBlank String title,
            @NotBlank String body
    ) {
        Article article = articleService.write(rq.getMember(), title, body);

        return rq.redirect("/", "%d번 게시물 생성되었습니다.".formatted(article.getId()));
    }

    @GetMapping("/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {
       Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/article/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable long id) {
        Article article = articleService.findById(id).get();

        if (!articleService.canDelete(rq.getMember(), article)) throw new RuntimeException("삭제권한이 없습니다.");

        articleService.delete(article);

        return rq.redirect("/", "%d번 게시물 삭제되었습니다.".formatted(id));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    String showModify(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        if (!articleService.canModify(rq.getMember(), article)) throw new RuntimeException("수정권한이 없습니다.");

        model.addAttribute("article", article);

        return "article/article/modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/modify/{id}")
    String modify(@PathVariable long id, @NotBlank String title, @NotBlank String body) {
        Article article = articleService.findById(id).get();

        if (!articleService.canModify(rq.getMember(), article)) throw new RuntimeException("수정권한이 없습니다.");

        articleService.modify(article, title, body);

        return rq.redirect("/", "%d번 게시물 수정되었습니다.".formatted(id));
    }

}

