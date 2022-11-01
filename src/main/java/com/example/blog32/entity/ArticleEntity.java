package com.example.blog32.entity;

import javax.persistence.*;

@Entity
public class ArticleEntity {
    @Id  //создаем колонку ИД в таблице базы данных и пишем что он должен сам увеличиваться на 1
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    @Column(name = "content", columnDefinition = "text")
    String content;
    String author;

    public ArticleEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
