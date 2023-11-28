package com.example.likelionspringboot.domain.article.article.repository;

import com.example.likelionspringboot.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findFirstByOrderByIdDesc();
}