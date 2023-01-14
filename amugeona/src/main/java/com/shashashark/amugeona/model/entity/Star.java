package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(StarPK.class)
public class Star implements Serializable {

    @Id
    private Long recipeSeq;
    @Id
    private Long userSeq;
    private Integer rate;

    public void modify(Integer rate) {
        this.rate = rate;
    }
}
