package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantCustomRepository {
}
