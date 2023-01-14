package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.IngredientDto;
import com.shashashark.amugeona.model.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    List<IngredientDto> selectAll(String name);

    default IngredientDto toDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .ingredientSeq(ingredient.getIngredientSeq())
                .name(ingredient.getName())
                .build();
    }

}
