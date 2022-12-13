package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Optional<RecipeDto> selectOne(Long recipeSeq);

    List<RecipeDto> selectAll();

    default RecipeDto toDto(Recipe recipe) {
        return new RecipeDto().builder()
                .recipeSeq(recipe.getRecipeSeq())
                .title(recipe.getTitle())
                .thumbnail(recipe.getThumbnail())
                .build();
    }
}