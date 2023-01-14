package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.ReplyCommentDto;
import com.shashashark.amugeona.model.dto.ReplyCommentUpdateParam;
import com.shashashark.amugeona.model.entity.ReplyComment;

import java.util.List;
import java.util.Optional;

public interface ReplyCommentService {
    Optional<ReplyCommentDto> selectOne(Long replySeq);


    List<ReplyCommentDto> selectAll(Long rootSeq, int page);

    void writeReply(ReplyCommentDto replyCommentDto);

    void updateReply(ReplyCommentUpdateParam param);

    void deleteReply(Long replySeq);

    default ReplyCommentDto toDto(ReplyComment replyComment) {
        return ReplyCommentDto.builder()
                .replySeq(replyComment.getReplySeq())
                .rootSeq(replyComment.getRootSeq())
                .userSeq(replyComment.getUserSeq())
                .nickname(replyComment.getUser().getNickname())
                .content(replyComment.getContent())
                .createdAt(replyComment.getCreatedAt())
                .modifiedAt(replyComment.getModifiedAt())
                .build();
    }

    default ReplyComment toEntity(ReplyCommentDto replyCommentDto) {
        return ReplyComment.builder()
                .replySeq(replyCommentDto.getReplySeq())
                .rootSeq(replyCommentDto.getRootSeq())
                .userSeq(replyCommentDto.getUserSeq())
                .content(replyCommentDto.getContent())
                .build();
    }
}
