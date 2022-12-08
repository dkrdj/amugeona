package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.CommentDto;
import com.shashashark.amugeona.model.dto.CommentUpdateParam;
import com.shashashark.amugeona.model.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentDto> selectOne(Long commentSeq);

    List<CommentDto> selectAll(Long articleSeq);

    void writeComment(CommentDto commentDto);

    void updateComment(CommentUpdateParam param);

    void deleteComment(Long commentSeq);

    default CommentDto toDto(Comment comment) {
        return new CommentDto().builder()
                .commentSeq(comment.getCommentSeq())
                .articleSeq(comment.getArticleSeq())
                .userSeq(comment.getUserSeq())
                .nickname(comment.getUser().getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    default Comment toEntity(CommentDto commentDto) {
        return new Comment().builder()
                .commentSeq(commentDto.getCommentSeq())
                .articleSeq(commentDto.getArticleSeq())
                .userSeq(commentDto.getUserSeq())
                .content(commentDto.getContent())
                .build();
    }
}
