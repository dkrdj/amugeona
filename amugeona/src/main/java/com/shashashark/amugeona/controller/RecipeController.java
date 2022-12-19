package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.service.RecipeService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@CrossOrigin
public class RecipeController {
    private static final String HEADER_AUTH = "access-token";
    private final RecipeService recipeService;

    private final JwtUtil jwtUtil;

    @GetMapping("/list")
    public ResponseEntity<List<RecipeDto>> list(HttpServletRequest request, String orderBy, int page) {
        Long userSeq = jwtUtil.getToken(request.getHeader(HEADER_AUTH)).getUserSeq();
        return new ResponseEntity<>(recipeService.selectAll(userSeq, orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/search-title")
    public ResponseEntity<List<RecipeDto>> searchTitle(HttpServletRequest request, String orderBy, String title, int page) {
        Long userSeq = jwtUtil.getToken(request.getHeader(HEADER_AUTH)).getUserSeq();
        return new ResponseEntity<>(recipeService.searchTitle(userSeq, orderBy, title, page), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<RecipeDto> detail(Long recipeSeq) {
        return new ResponseEntity<>(recipeService.selectOne(recipeSeq).orElseThrow(), HttpStatus.OK);
    }
}
