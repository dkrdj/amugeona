package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.StarDto;
import com.shashashark.amugeona.model.entity.Star;
import com.shashashark.amugeona.model.param.StarUpdateParam;

import java.util.Optional;

public interface StarService {
    Optional<StarDto> selectOne(Long userSeq, Long recipeSeq);

    void writeStar(StarDto starDto);

    void updateStar(StarUpdateParam param);

    void deleteStar(Long starSeq);

    default StarDto toDto(Star star) {
        return new StarDto().builder()
                .starSeq(star.getStarSeq())
                .recipeSeq(star.getRecipeSeq())
                .userSeq(star.getUserSeq())
                .rate(star.getRate())
                .build();
    }

    default Star toEntity(StarDto starDto) {
        return new Star().builder()
                .starSeq(starDto.getStarSeq())
                .recipeSeq(starDto.getRecipeSeq())
                .userSeq(starDto.getUserSeq())
                .rate(starDto.getRate())
                .build();
    }
}
