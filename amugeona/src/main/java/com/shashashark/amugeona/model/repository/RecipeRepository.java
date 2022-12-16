package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByTitleLike(String title, Pageable pageable);
}
