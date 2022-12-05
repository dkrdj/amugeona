package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.BoardDto;
import com.shashashark.amugeona.model.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Optional<BoardDto> selectOne(Long boardSeq);

    List<BoardDto> selectAll();

    default BoardDto toDto(Board board) {
        return BoardDto.builder()
                .boardSeq(board.getBoardSeq())
                .title(board.getTitle())
                .intro(board.getIntro())
                .build();
    }

}
