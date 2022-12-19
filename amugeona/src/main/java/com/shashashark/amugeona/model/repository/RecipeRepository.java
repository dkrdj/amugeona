package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, CustomRecipeRepository {
}
