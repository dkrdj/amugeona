package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
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
}
