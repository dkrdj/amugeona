package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
