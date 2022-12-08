package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.StarDto;
import com.shashashark.amugeona.model.dto.StarUpdateParam;
import com.shashashark.amugeona.model.entity.Star;

import java.util.Optional;

public interface StarService {
    Optional<StarDto> selectOne(Long userSeq, Long articleSeq);

    void writeStar(StarDto starDto);

    void updateStar(StarUpdateParam param);

    void deleteStar(Long starSeq);

    default StarDto toDto(Star star) {
        return new StarDto().builder()
                .starSeq(star.getStarSeq())
                .articleSeq(star.getArticleSeq())
                .userSeq(star.getUserSeq())
                .rate(star.getRate())
                .build();
    }

    default Star toEntity(StarDto starDto) {
        return new Star().builder()
                .starSeq(starDto.getStarSeq())
                .articleSeq(starDto.getArticleSeq())
                .userSeq(starDto.getUserSeq())
                .rate(starDto.getRate())
                .build();
    }
}
