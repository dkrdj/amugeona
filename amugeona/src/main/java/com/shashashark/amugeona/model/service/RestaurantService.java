package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.RestaurantDto;
import com.shashashark.amugeona.model.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto> selectAll(Double longitude, Double latitude);

    void addImage(Long restaurantSeq, String image);

    default RestaurantDto toDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .restaurantSeq(restaurant.getRestaurantSeq())
                .title(restaurant.getTitle())
                .category(restaurant.getCategory())
                .starRating(restaurant.getStarRating())
                .starCnt(restaurant.getStarCnt())
                .address(restaurant.getAddress())
                .number(restaurant.getNumber())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .image(restaurant.getImage())
                .build();
    }
}
