package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleSeq(Long articleSeq);
}
