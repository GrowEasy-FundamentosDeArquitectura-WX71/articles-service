package com.groweasy.articlesservice.service;

import com.groweasy.articlesservice.model.Article;

public interface ArticlesService {
    Article createArticle(Article article);

    Article getArticleById(Long id);
}
