package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.RecipeContentDto;
import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.dto.RecipeImageDto;
import com.shashashark.amugeona.model.dto.RecipeIngredientDto;
import com.shashashark.amugeona.model.entity.Recipe;
import com.shashashark.amugeona.model.entity.RecipeContent;
import com.shashashark.amugeona.model.entity.RecipeImage;
import com.shashashark.amugeona.model.entity.RecipeIngredient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface RecipeService {
    Optional<RecipeDto> selectOne(Long recipeSeq);


    default RecipeDto toDto(Recipe recipe) {
        return RecipeDto.builder()
                .recipeSeq(recipe.getRecipeSeq())
                .title(recipe.getTitle())
                .thumbnail(recipe.getThumbnail())
                .recipeIngredients(recipe.getRecipeIngredients().stream().map(this::toDto).collect(Collectors.toList()))
                .recipeContents(recipe.getRecipeContents().stream().map(this::toDto).collect(Collectors.toList()))
                .recipeImages(recipe.getRecipeImages().stream().map(this::toDto).collect(Collectors.toList()))
                .starRating(recipe.getStarRating())
                .starCnt(recipe.getStarCnt())
                .build();
    }

    default RecipeIngredientDto toDto(RecipeIngredient recipeIngredient) {
        return RecipeIngredientDto.builder()
                .recipeSeq(recipeIngredient.getRecipeSeq())
                .ingredientSeq(recipeIngredient.getIngredientSeq())
                .name(recipeIngredient.getIngredient().getName())
                .build();
    }

    default RecipeContentDto toDto(RecipeContent recipeContent) {
        return RecipeContentDto.builder()
                .recipeSeq(recipeContent.getRecipeSeq())
                .contentIdx(recipeContent.getContentIdx())
                .contentSeq(recipeContent.getContentSeq())
                .content(recipeContent.getContent())
                .build();
    }

    default RecipeImageDto toDto(RecipeImage recipeImage) {
        return RecipeImageDto.builder()
                .recipeSeq(recipeImage.getRecipeSeq())
                .url(recipeImage.getUrl())
                .imgSeq(recipeImage.getImgSeq())
                .contentIdx(recipeImage.getContentIdx())
                .build();
    }


    List<RecipeDto> selectAll(Long userSeq, String orderBy, int page);

    List<RecipeDto> search(Long userSeq, String orderBy, String title, int page);
}
