package com.example.likelionspringboot.domain.article.article.controller;

import com.example.likelionspringboot.domain.article.article.entity.Article;
import com.example.likelionspringboot.domain.article.article.service.ArticleService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    @GetMapping("/article/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/detail";
    }

    @PostMapping("/article/write")
    String write(
            @NotBlank String title,
            @NotBlank String body
    ) {
        Article article = articleService.write(title, body);

        String message = "id %d, article created".formatted(article.getId());

        return "redirect:/article/list?msg=" + message;
    }

    @GetMapping("/article/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "article/list";
    }

    @GetMapping("/article/delete/{id}")
    String delete(@PathVariable long id) {
        articleService.delete(id);

        String msg = "id %d, article deleted".formatted(id);

        return "redirect:/article/list?msg=" + msg;
    }

    @GetMapping("/article/modify/{id}")
    String showModify(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/modify";
    }

    @PostMapping("/article/modify/{id}")
    String modify(@PathVariable long id, @NotBlank String title, @NotBlank String body) {
        articleService.modify(id, title, body);

        String msg = "id %d, article modified".formatted(id);

        return "redirect:/article/list?msg=" + msg;
    }

}

