package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_seq")
    private Long recipeSeq;

    private String title;
    private String thumbnail;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_seq")
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_seq")
    private List<RecipeImage> recipeImgs;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_seq")
    private List<RecipeContent> recipeContents;

}
