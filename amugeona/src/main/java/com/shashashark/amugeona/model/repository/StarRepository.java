package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findByUserSeqAndRecipeSeq(Long userSeq, Long recipeSeq);

    List<Star> findByRecipeSeq(Long recipeSeq);
}
