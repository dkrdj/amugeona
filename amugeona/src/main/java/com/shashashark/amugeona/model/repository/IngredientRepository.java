package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Ingredient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameContaining(String name, Pageable pageable);
}
