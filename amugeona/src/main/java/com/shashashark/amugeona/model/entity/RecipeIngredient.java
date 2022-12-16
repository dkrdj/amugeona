package com.shashashark.amugeona.model.entity;

import com.shashashark.amugeona.model.param.RecipeIngredientPK;
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
@Table(name = "recipe_ingre")
@IdClass(RecipeIngredientPK.class)
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_seq")
    private Long recipeSeq;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_seq")
    private Long ingredientSeq;

    @ManyToOne
    @JoinColumn(name = "ingredient_seq", insertable = false, updatable = false)
    private Ingredient ingredient;
}
