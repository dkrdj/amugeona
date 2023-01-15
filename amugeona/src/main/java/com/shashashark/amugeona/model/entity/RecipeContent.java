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
public class RecipeContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentSeq;
    @Column(name = "recipe_seq")
    private Long recipeSeq;
    private String content;
    private Long contentIdx;
}
