package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.ArticleLikeDto;
import com.shashashark.amugeona.model.entity.ArticleLike;

public interface ArticleLikeService {

    boolean findOne(Long userSeq, Long articleSeq);

    void writeLike(ArticleLikeDto articleLikeDto);

    void deleteLike(Long userSeq, Long articleSeq);

    default ArticleLikeDto toDto(ArticleLike articleLike) {
        return new ArticleLikeDto().builder()
                .likeSeq(articleLike.getLikeSeq())
                .userSeq(articleLike.getUserSeq())
                .articleSeq(articleLike.getArticleSeq())
                .build();
    }

    default ArticleLike toEntity(ArticleLikeDto articleLikeDto) {
        return new ArticleLike().builder()
                .userSeq(articleLikeDto.getUserSeq())
                .articleSeq(articleLikeDto.getArticleSeq())
                .build();
    }
}
