package com.shashashark.amugeona.model.entity;

import com.shashashark.amugeona.model.param.InediblePK;
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
@IdClass(InediblePK.class)
public class Inedible implements Serializable {

    @Id
    @Column(name = "ingredient_seq")
    private Long ingredientSeq;

    @OneToOne
    @JoinColumn(name = "ingredient_seq", insertable = false, updatable = false)
    private Ingredient ingredient;

    @Id
    private Long userSeq;
}
