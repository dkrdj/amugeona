package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.CommentDto;
import com.shashashark.amugeona.model.entity.Comment;
import com.shashashark.amugeona.model.param.CommentUpdateParam;
import com.shashashark.amugeona.model.repository.CommentRepository;
import com.shashashark.amugeona.model.service.CommentService;
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
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Optional<CommentDto> selectOne(Long commentSeq) {
        return Optional.ofNullable(toDto(commentRepository.findById(commentSeq).orElseThrow()));
    }

    @Override
    public List<CommentDto> selectAll(Long articleSeq, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return commentRepository.findAllByArticleSeq(articleSeq, pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeComment(CommentDto commentDto) {
        commentRepository.save(toEntity(commentDto));
    }

    @Override
    public void updateComment(CommentUpdateParam param) {
        Comment comment = commentRepository.findById(param.getCommentSeq()).orElseThrow();
        comment.modify(param.getContent());
    }

    @Override
    public void deleteComment(Long commentSeq) {
        commentRepository.deleteById(commentSeq);
    }
}
