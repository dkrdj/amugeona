package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    Optional<ArticleLike> findByUserSeqAndArticleSeq(Long userSeq, Long articleSeq);

    void deleteByUserSeqAndArticleSeq(Long userSeq, Long articleSeq);

}
