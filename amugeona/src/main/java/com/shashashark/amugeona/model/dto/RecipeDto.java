package com.shashashark.amugeona.model.dto;

import com.shashashark.amugeona.model.entity.RecipeContent;
import com.shashashark.amugeona.model.entity.RecipeImage;
import com.shashashark.amugeona.model.entity.RecipeIngredient;
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

    private List<RecipeIngredient> recipeIngredients;
    private List<RecipeContent> recipeContents;
    private List<RecipeImage> recipeImages;
}
