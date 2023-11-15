package com.example.likelionspringboot.domain.article.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Article {
    private Long id;
    private String title;
    private String body;

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
