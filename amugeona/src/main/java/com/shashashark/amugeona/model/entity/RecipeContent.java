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
@Table(name = "recipe_content")
public class RecipeContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentSeq;
    private Long recipe_seq;
    private String content;
    private Long contentIdx;
}
