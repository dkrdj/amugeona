package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.IngredientDto;
import com.shashashark.amugeona.model.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Optional<IngredientDto> selectOne(String name);

    List<IngredientDto> selectAll();

    void writeIngredient(IngredientDto ingredientDto);

//    void updateIngredient(IngredientDto ingredientDto);
//
//    void deleteIngredient(Long ingredientSeq);

    default IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto().builder()
                .ingredientSeq(ingredient.getIngredientSeq())
                .name(ingredient.getName())
                .build();
    }

    default Ingredient toEntity(IngredientDto ingredientDto) {
        return new Ingredient().builder()
                .ingredientSeq(ingredientDto.getIngredientSeq())
                .name(ingredientDto.getName())
                .build();
    }
}
