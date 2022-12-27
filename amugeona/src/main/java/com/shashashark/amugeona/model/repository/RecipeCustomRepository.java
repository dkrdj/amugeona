package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Recipe;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeCustomRepository {
    List<Recipe> findAll(Long userSeq, Pageable pageable);

    List<Recipe> searchTitle(String title, Long userSeq, Pageable pageable);
}
