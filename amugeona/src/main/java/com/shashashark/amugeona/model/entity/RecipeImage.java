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
@Table(name = "recipe_img")
public class RecipeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgSeq;

    private Long recipe_seq;
    private String url;
    private Long contentIdx;
}
