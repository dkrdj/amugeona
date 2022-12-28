package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.RestaurantDto;
import com.shashashark.amugeona.model.entity.Restaurant;
import com.shashashark.amugeona.model.repository.RestaurantRepository;
import com.shashashark.amugeona.model.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantDto> selectAll(Double longitude, Double latitude) {
        return restaurantRepository.findAllByDistance(longitude, latitude).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void addImage(Long restaurantSeq, String image) {
        Restaurant restaurant = restaurantRepository.findById(restaurantSeq).orElseThrow();
        restaurant.setImage(image);
    }
}
