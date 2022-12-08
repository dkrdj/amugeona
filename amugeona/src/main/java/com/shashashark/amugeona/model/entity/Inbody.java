package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Inbody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    private Double height;
    private Double weight;
    private Double muscle;
    private Double fat;

    public void modify(Double height, Double weight, Double muscle, Double fat) {
        this.height = height;
        this.weight = weight;
        this.muscle = muscle;
        this.fat = fat;
    }
}

