package com.groweasy.articlesservice.repository;

import com.groweasy.articlesservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

    Boolean existsByTitle(String title);
}
