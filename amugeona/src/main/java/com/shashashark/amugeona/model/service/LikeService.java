package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.LikeDto;
import com.shashashark.amugeona.model.entity.Like;

import java.util.Optional;

public interface LikeService {

    Optional<LikeDto> selectOne(Long userSeq, Long articleSeq);

    void writeLike(LikeDto likeDto);

    void deleteLike(Long userSeq, Long articleSeq);

    default LikeDto toDto(Like like) {
        return new LikeDto().builder()
                .likeSeq(like.getLikeSeq())
                .userSeq(like.getUserSeq())
                .articleSeq(like.getArticleSeq())
                .build();
    }

    default Like toEntity(LikeDto likeDto) {
        return new Like().builder()
                .userSeq(likeDto.getUserSeq())
                .articleSeq(likeDto.getArticleSeq())
                .build();
    }
}
