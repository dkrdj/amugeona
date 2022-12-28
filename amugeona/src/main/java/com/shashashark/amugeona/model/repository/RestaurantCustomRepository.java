package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Restaurant;

import java.util.List;

public interface RestaurantCustomRepository {
    List<Restaurant> findAllByDistance(Double longitude, Double latitude);
}
