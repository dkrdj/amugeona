package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserSeqAndArticleSeq(Long userSeq, Long articleSeq);

    Optional<Like> deleteByUserSeqAndArticleSeq(Long userSeq, Long articleSeq);

}
