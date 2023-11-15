package com.example.likelionspringboot.global.app;

import com.example.likelionspringboot.domain.article.article.entity.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    List<Article> articles() {
        return new ArrayList<>();
    }
}
