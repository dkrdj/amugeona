package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.ReplyComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {
    List<ReplyComment> findAllByRootSeq(Long rootSeq, Pageable pageable);
}
