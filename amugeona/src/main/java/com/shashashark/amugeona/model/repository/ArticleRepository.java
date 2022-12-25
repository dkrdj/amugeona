package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByBoardSeqAndTitleContaining(Long boardSeq, String title, Pageable pageable);

    List<Article> findAllByBoardSeqAndContentContaining(Long boardSeq, String title, Pageable pageable);

    List<Article> findAllByBoardSeq(Long boardSeq, Pageable pageable);

    List<Article> findAllBy(Pageable pageable);
}
