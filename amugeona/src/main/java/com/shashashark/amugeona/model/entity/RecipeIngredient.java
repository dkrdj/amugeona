package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(RecipeIngredientPK.class)
public class RecipeIngredient implements Serializable {

    @Id
    @Column(name = "recipe_seq")
    private Long recipeSeq;

    @Id
    @Column(name = "ingredient_seq")
    private Long ingredientSeq;

    @ManyToOne
    @JoinColumn(name = "ingredient_seq", insertable = false, updatable = false)
    private Ingredient ingredient;
}
