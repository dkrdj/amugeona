package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByTitleLike(String title, Pageable pageable);

    List<Article> findAllByContentLike(String title, Pageable pageable);

    List<Article> findAllSorted(Long boardSeq, Pageable pageable, Sort sort);

}
