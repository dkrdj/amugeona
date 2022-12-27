package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Inedible;
import com.shashashark.amugeona.model.entity.InediblePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InedibleRepository extends JpaRepository<Inedible, InediblePK> {
    List<Inedible> findAllByUserSeq(Long userSeq);

    void deleteByUserSeqAndIngredientSeq(Long userSeq, Long ingredientSeq);

    Optional<Inedible> findByUserSeqAndIngredientSeq(Long userSeq, Long ingredientSeq);
}
