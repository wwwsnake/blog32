package com.example.blog32.controller;


import com.example.blog32.entity.ArticleEntity;
import com.example.blog32.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //аннотация для спринга (как указатель на какое-то действие)
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepo articleRepo;

    //по ссылке 127.0.01:8080/article/addArticle отобразится "ok"
    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute ArticleEntity article){
        articleRepo.save(article);//добавляем инфу в базу данных
        return "ok";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
        //по ссылке 127.0.01:8080/article/hello отобразится "hello"
    }
}
