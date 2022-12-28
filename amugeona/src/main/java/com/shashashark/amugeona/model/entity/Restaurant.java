package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    private Long restaurantSeq;
    private String title;
    private String category;
    private Double starRating;
    private Integer starCnt;
    private String address;
    private String number;
    private Double longitude;
    private Double latitude;
    private String image;

    public void setImage(String image) {
        this.image = image;
    }
}
