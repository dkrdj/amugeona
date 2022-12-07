package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.BoardDto;
import com.shashashark.amugeona.model.repository.BoardRepository;
import com.shashashark.amugeona.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public Optional<BoardDto> selectOne(Long boardSeq) {
        return Optional.ofNullable(toDto(boardRepository.findById(boardSeq).orElseThrow()));
    }

    @Override
    public List<BoardDto> selectAll() {
        return boardRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}
