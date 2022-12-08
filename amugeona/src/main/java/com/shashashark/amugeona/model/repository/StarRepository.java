package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findByUserSeqAndArticleSeq(Long userSeq, Long articleSeq);
}
