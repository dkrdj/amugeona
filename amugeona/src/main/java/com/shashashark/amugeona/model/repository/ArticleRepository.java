package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByBoardSeq(Long boardSeq);


}
