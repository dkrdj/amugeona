package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.RestaurantDto;
import com.shashashark.amugeona.model.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> restaurants(Double longitude, Double latitude) {
        return new ResponseEntity<>(restaurantService.selectAll(longitude, latitude), HttpStatus.OK);
    }

    @PutMapping("/restaurants/{restaurantSeq}")
    public ResponseEntity<List<RestaurantDto>> setImage(String image, @PathVariable Long restaurantSeq) {
        restaurantService.addImage(restaurantSeq, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
