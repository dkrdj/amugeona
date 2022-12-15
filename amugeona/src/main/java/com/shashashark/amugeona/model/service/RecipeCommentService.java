package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.CommentUpdateParam;
import com.shashashark.amugeona.model.dto.RecipeCommentDto;
import com.shashashark.amugeona.model.entity.RecipeComment;

import java.util.List;
import java.util.Optional;

public interface RecipeCommentService {

    Optional<RecipeCommentDto> selectOne(Long commentSeq);

    List<RecipeCommentDto> selectAll(Long recipeSeq);

    void writeComment(RecipeCommentDto recipeCommentDto);

    void updateComment(CommentUpdateParam param);

    void deleteComment(Long commentSeq);

    default RecipeCommentDto toDto(RecipeComment recipeComment) {
        return new RecipeCommentDto().builder()
                .commentSeq(recipeComment.getCommentSeq())
                .recipeSeq(recipeComment.getRecipeSeq())
                .userSeq(recipeComment.getUserSeq())
                .nickname(recipeComment.getUser().getNickname())
                .content(recipeComment.getContent())
                .createdAt(recipeComment.getCreatedAt())
                .modifiedAt(recipeComment.getModifiedAt())
                .build();
    }

    default RecipeComment toEntity(RecipeCommentDto recipeCommentDto) {
        return new RecipeComment().builder()
                .commentSeq(recipeCommentDto.getCommentSeq())
                .recipeSeq(recipeCommentDto.getRecipeSeq())
                .userSeq(recipeCommentDto.getUserSeq())
                .content(recipeCommentDto.getContent())
                .build();
    }
}
