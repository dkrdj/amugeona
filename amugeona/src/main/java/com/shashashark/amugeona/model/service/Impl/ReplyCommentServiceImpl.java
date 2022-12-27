package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.ReplyCommentDto;
import com.shashashark.amugeona.model.dto.ReplyCommentUpdateParam;
import com.shashashark.amugeona.model.entity.ReplyComment;
import com.shashashark.amugeona.model.repository.ReplyCommentRepository;
import com.shashashark.amugeona.model.service.ReplyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommentServiceImpl implements ReplyCommentService {
    private final ReplyCommentRepository replyCommentRepository;

    @Override
    public Optional<ReplyCommentDto> selectOne(Long replySeq) {
        return Optional.ofNullable(toDto(replyCommentRepository.findById(replySeq).orElseThrow()));
    }

    @Override
    public List<ReplyCommentDto> selectAll(Long rootSeq, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return replyCommentRepository.findAllByRootSeq(rootSeq, pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeReply(ReplyCommentDto replyCommentDto) {
        replyCommentRepository.save(toEntity(replyCommentDto));
    }

    @Override
    public void updateReply(ReplyCommentUpdateParam param) {
        ReplyComment replyComment = replyCommentRepository.findById(param.getReplySeq()).orElseThrow();
        replyComment.modify(param.getContent());
    }

    @Override
    public void deleteReply(Long replySeq) {
        replyCommentRepository.deleteById(replySeq);
    }
}
