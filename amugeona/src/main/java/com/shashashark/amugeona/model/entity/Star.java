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
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starSeq;

    private Long articleSeq;
    private Long userSeq;
    private Integer rate;

    public void modify(Integer rate) {
        this.rate = rate;
    }
}
