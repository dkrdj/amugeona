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
public class Inedible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_seq")
    private Long ingredientSeq;

    @OneToOne
    @JoinColumn(name = "ingredient_seq")
    private Ingredient ingredient;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;
}
