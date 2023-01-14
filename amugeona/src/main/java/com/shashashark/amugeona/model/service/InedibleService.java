package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.InedibleDto;
import com.shashashark.amugeona.model.entity.Inedible;

import java.util.List;
import java.util.Optional;

public interface InedibleService {
    Optional<InedibleDto> selectOne(Long userSeq, Long ingredientSeq);

    List<InedibleDto> selectAll(Long userSeq);

    void writeInedible(InedibleDto inedibleDto);

    void deleteInedible(Long userSeq, Long ingredientSeq);

    default InedibleDto toDto(Inedible inedible) {
        return InedibleDto.builder()
                .ingredientSeq(inedible.getIngredientSeq())
                .userSeq(inedible.getUserSeq())
                .name(inedible.getIngredient().getName())
                .build();
    }

    default Inedible toEntity(InedibleDto inedibleDto) {
        return Inedible.builder()
                .ingredientSeq(inedibleDto.getIngredientSeq())
                .userSeq(inedibleDto.getUserSeq())
                .build();
    }

}
