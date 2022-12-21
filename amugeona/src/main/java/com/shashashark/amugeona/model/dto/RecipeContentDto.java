package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeContentDto {
    private Long contentSeq;
    private Long recipeSeq;
    private String content;
    private Long contentIdx;
}
