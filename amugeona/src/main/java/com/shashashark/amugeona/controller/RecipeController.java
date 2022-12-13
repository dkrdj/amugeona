package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@CrossOrigin
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/list")
    public ResponseEntity<List<RecipeDto>> list(HttpServletRequest request) {
        Long userSeq = jwtUtil.getToken(request.getHeader(HEADER_AUTH)).getUserSeq();
        return new ResponseEntity<>(recipeService.selectAll(userSeq), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<RecipeDto> detail(Long recipeSeq) {
        return new ResponseEntity<>(recipeService.selectOne(recipeSeq).orElseThrow(), HttpStatus.OK);
    }
}
