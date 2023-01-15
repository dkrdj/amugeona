package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private Long recipeSeq;
    private String title;
    private String thumbnail;
    private Double starRating;
    private Integer starCnt;
    private List<RecipeIngredientDto> recipeIngredients;
    private List<RecipeContentDto> recipeContents;
    private List<RecipeImageDto> recipeImages;
}
