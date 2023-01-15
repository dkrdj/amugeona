package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeImageDto {
    private Long imgSeq;
    private Long recipeSeq;
    private String url;
    private Long contentIdx;
}
