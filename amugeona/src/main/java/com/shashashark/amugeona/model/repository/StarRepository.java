package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long> {
    Star findByUserSeqAndArticleSeq(Long userSeq, Long articleSeq);
}
