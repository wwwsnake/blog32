package com.example.blog32.controller;


import com.example.blog32.entity.ArticleEntity;
import com.example.blog32.repository.ArticleRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Base64;

@RestController //аннотация для спринга (как указатель на какое-то действие)
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepo articleRepo;

    //по ссылке 127.0.01:8080/article/addArticle отобразится "ok"
    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute ArticleEntity article){
        if(articleRepo.findByTitle(article.getTitle())!=null) return "Такое уже есть!";
        String html = article.getContent();
        Document document = Jsoup.parse(html);
        Elements images = document.select("img");
        Element img = images.first();
        String src = img.attr("src");
        String base64 = src.split(",")[1];
        byte[] image = Base64.getDecoder().decode(base64);//превратили картинку в байты
        try {
            FileOutputStream fos = new FileOutputStream("C://Java.test.jpg");
            fos.write(image);
            fos.close();//сохранили картинку в файл и закрыли его
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // articleRepo.save(article);//добавляем инфу в базу данных
        return "OK";
    }
//получаем список всех статей в базе:
    @GetMapping("/getArticles")
    public ResponseEntity getArticles(){
        ArrayList<ArticleEntity> articles = (ArrayList<ArticleEntity>) articleRepo.findAll(); //получить все из базы
        return ResponseEntity.ok(articles);
    }
@GetMapping("getArticleById")
public ResponseEntity getArticleById(@ModelAttribute ArticleEntity article){
        return ResponseEntity.ok(articleRepo.findById(article.getId()));//получаем конкретную статью по id
}

    @GetMapping("/hello")
    public String hello() {
        return "hello";
        //по ссылке 127.0.01:8080/article/hello отобразится "hello"
    }
}
