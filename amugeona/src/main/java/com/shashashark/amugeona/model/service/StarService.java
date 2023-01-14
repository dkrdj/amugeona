package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.StarDto;
import com.shashashark.amugeona.model.dto.StarUpdateParam;
import com.shashashark.amugeona.model.entity.Star;

import java.util.List;

public interface StarService {
    List<StarDto> selectAll(Long recipeSeq);

    void writeStar(StarDto starDto);

    void updateStar(StarUpdateParam param);

    void deleteStar(Long userSeq, Long recipeSeq);

    default StarDto toDto(Star star) {
        return StarDto.builder()
                .recipeSeq(star.getRecipeSeq())
                .userSeq(star.getUserSeq())
                .rate(star.getRate())
                .build();
    }

    default Star toEntity(StarDto starDto) {
        return Star.builder()
                .recipeSeq(starDto.getRecipeSeq())
                .userSeq(starDto.getUserSeq())
                .rate(starDto.getRate())
                .build();
    }
}
