package com.example.blog32.repository;

import com.example.blog32.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends CrudRepository<ArticleEntity, Integer> {
ArticleEntity findByTitle(String title);
}

