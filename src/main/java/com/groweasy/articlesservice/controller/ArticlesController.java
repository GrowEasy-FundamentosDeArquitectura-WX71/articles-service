package com.groweasy.articlesservice.controller;

import com.groweasy.articlesservice.model.Article;
import com.groweasy.articlesservice.repository.ArticlesRepository;
import com.groweasy.articlesservice.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;
    private final ArticlesRepository articlesRepository;

    public ArticlesController(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    //@GetMapping("/articles")
    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return new ResponseEntity<List<Article>>(articlesRepository.findAll(), HttpStatus.OK);
    }

    //@GetMapping("/articles/{id}")
    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id) {
        Article article =  articlesService.getArticleById(id);
        if(null == article){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);

        //return new ResponseEntity<Article>(articlesRepository.findById(id).get(), HttpStatus.OK);
    }

    //@PostMapping("/articles")
    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        try {
            validateArticle(article);
            existsByTitle(article);
            //existsByNameAndPrice(article);
            return new ResponseEntity<Article>(articlesService.createArticle(article), HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void validateArticle(Article article){
        if(article.getTitle() == null || article.getTitle().isEmpty()){
            throw new RuntimeException("El título del artículo es obligatorio");
        }
    }

    private void existsByTitle(Article article){
        if(articlesRepository.existsByTitle(article.getTitle())){
            throw new RuntimeException("El título del artículo ya existe");
        }
    }
}
