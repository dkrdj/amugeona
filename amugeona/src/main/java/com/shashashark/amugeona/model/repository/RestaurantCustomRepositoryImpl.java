package com.shashashark.amugeona.model.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shashashark.amugeona.model.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shashashark.amugeona.model.entity.QRestaurant.restaurant;

@Repository
@RequiredArgsConstructor
public class RestaurantCustomRepositoryImpl implements RestaurantCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Restaurant> findAllByDistance(Double longitude, Double latitude) {
        return query
                .select(restaurant)
                .from(restaurant)
                .where(restaurant.longitude.goe(longitude - 0.0017)
                        .and(restaurant.longitude.loe(longitude + 0.0017))
                        .and(restaurant.latitude.goe(latitude - 0.0017))
                        .and(restaurant.latitude.loe(latitude + 0.0017)))
                .fetch();
    }
}
