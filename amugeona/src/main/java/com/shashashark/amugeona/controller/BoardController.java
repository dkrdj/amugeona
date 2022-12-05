package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.BoardDto;
import com.shashashark.amugeona.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public ResponseEntity<List<BoardDto>> getAllBoard() {
        return new ResponseEntity<>(boardService.selectAll(), HttpStatus.OK);
    }

    @GetMapping("/{boardSeq}")
    public ResponseEntity<BoardDto> getBoard(@RequestParam Long boardSeq) {
        return new ResponseEntity<>(boardService.selectOne(boardSeq).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addBoard() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
