package com.example.likelionspringboot.domain.article.article.controller;

import com.example.likelionspringboot.domain.article.article.entity.Article;
import com.example.likelionspringboot.domain.article.article.service.ArticleService;
import com.example.likelionspringboot.global.resultData.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    @PostMapping("/article/write")
    @ResponseBody
    ResultData write(
            String title,
            String body
    ) {
        Article article = articleService.write(title, body);

        ResultData<Article> result = new ResultData<>(
                "success-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                    article
        );

        return result;
    }

    @GetMapping("/article/getLastArticle")
    @ResponseBody
    Article getLastArticle() {
        return articleService.findLastArticle();
    }

    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> getArticles() {
        return articleService.findAll();
    }

}

