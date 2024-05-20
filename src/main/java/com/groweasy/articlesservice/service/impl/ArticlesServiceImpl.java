package com.groweasy.articlesservice.service.impl;

import com.groweasy.articlesservice.model.Article;
import com.groweasy.articlesservice.repository.ArticlesRepository;
import com.groweasy.articlesservice.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    @Autowired
    private ArticlesRepository articlesRepository;

    @Override
    public Article createArticle(Article article) {
        return articlesRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        return articlesRepository.findById(id).orElse(null);
    }
}
