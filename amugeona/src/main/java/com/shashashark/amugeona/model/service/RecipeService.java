package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Optional<RecipeDto> selectOne(Long recipeSeq);

    List<RecipeDto> selectAll(Long userSeq);

    default RecipeDto toDto(Recipe recipe) {
        return new RecipeDto().builder()
                .recipeSeq(recipe.getRecipeSeq())
                .title(recipe.getTitle())
                .thumbnail(recipe.getThumbnail())
                .recipeIngredients(recipe.getRecipeIngredients())
                .recipeContents(recipe.getRecipeContents())
                .recipeImages(recipe.getRecipeImgs())
                .starRating(recipe.getStarRating())
                .starCnt(recipe.getStarCnt())
                .build();
    }
}
