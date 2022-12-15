package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.RecipeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Long> {
    List<RecipeComment> findAllByRecipeSeq(Long recipeSeq);
}
