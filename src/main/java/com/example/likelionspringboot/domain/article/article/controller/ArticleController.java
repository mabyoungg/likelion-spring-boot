package com.example.likelionspringboot.domain.article.article.controller;

import com.example.likelionspringboot.domain.article.article.entity.Article;
import com.example.likelionspringboot.domain.article.article.service.ArticleService;
import com.example.likelionspringboot.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class ArticleController {
    private final ArticleService articleService;
    private final Rq rq;

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

//    @PostMapping("/article/write2")
//    @SneakyThrows
//    void write2(
//            HttpServletRequest req,
//            HttpServletResponse resp
//    ) {
//        String title = req.getParameter("title");
//        String body = req.getParameter("body");
//
//        Article article = articleService.write(title, body);
//
//        ResultData<Article> resultData = new ResultData<>(
//                "S-1",
//                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
//                article
//        );
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().println(objectMapper.writeValueAsString(resultData));
//    }

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

    @GetMapping("/article/articleServicePointer")
    @ResponseBody
    String articleServicePointer() {
        return articleService.toString();
    }

    @GetMapping("/article/httpServletRequestPointer")
    @ResponseBody
    String httpServletRequestPointer(HttpServletRequest req) {
        return req.toString();
    }

    @GetMapping("/article/httpServletResponsePointer")
    @ResponseBody
    String httpServletResponsePointer(HttpServletResponse resp) {
        return resp.toString();
    }

    @GetMapping("/article/rqPointer")
    @ResponseBody
    String rqPointer() {
        return rq.toString();
    }

    @GetMapping("/article/rqTest")
    String showRqTest(Model model) {
        String rqToString = rq.toString();

        model.addAttribute("rqToString", rqToString);

        return "article/rqTest";
    }

}

