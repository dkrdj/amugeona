package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "recipe_ingre")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeSeq;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_seq")
    private Long ingredientSeq;

    @ManyToOne
    @JoinColumn(name = "ingredient_seq", insertable = false, updatable = false)
    private Ingredient ingredient;
}
