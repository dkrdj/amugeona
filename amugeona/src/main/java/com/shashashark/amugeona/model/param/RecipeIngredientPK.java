package com.shashashark.amugeona.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientPK implements Serializable {
    private Long ingredientSeq;
    private Long recipeSeq;
}
