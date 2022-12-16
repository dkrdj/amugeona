package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.RecipeCommentDto;
import com.shashashark.amugeona.model.entity.RecipeComment;
import com.shashashark.amugeona.model.param.CommentUpdateParam;
import com.shashashark.amugeona.model.repository.RecipeCommentRepository;
import com.shashashark.amugeona.model.service.RecipeCommentService;
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
public class RecipeCommentServiceImpl implements RecipeCommentService {

    private final RecipeCommentRepository recipeCommentRepository;


    @Override
    public Optional<RecipeCommentDto> selectOne(Long commentSeq) {
        return Optional.ofNullable(toDto(recipeCommentRepository.findById(commentSeq).orElseThrow()));
    }

    @Override
    public List<RecipeCommentDto> selectAll(Long recipeSeq, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return recipeCommentRepository.findAllByRecipeSeq(recipeSeq, pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeComment(RecipeCommentDto recipeCommentDto) {
        recipeCommentRepository.save(toEntity(recipeCommentDto));
    }

    @Override
    public void updateComment(CommentUpdateParam param) {
        RecipeComment comment = recipeCommentRepository.findById(param.getCommentSeq()).orElseThrow();
        comment.modify(param.getContent());
    }

    @Override
    public void deleteComment(Long commentSeq) {
        recipeCommentRepository.deleteById(commentSeq);
    }
}
