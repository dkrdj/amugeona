package com.shashashark.amugeona.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicInsert
@ToString
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_seq")
    private Long recipeSeq;
    private String title;
    private String thumbnail;
    private Double starRating;
    private Integer starCnt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_seq", insertable = false, updatable = false)
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_seq", insertable = false, updatable = false)
    private List<RecipeImage> recipeImages;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_seq", insertable = false, updatable = false)
    private List<RecipeContent> recipeContents;

    public void updateStar(Double starRating, Integer starCnt) {
        this.starRating = starRating;
        this.starCnt = starCnt;
    }
}
