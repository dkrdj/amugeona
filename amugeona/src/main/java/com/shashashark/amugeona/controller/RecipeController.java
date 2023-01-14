package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.config.jwt.JwtProperties;
import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.service.RecipeService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    private final JwtUtil jwtUtil;

    @GetMapping("/recipes")
    public ResponseEntity<List<RecipeDto>> list(HttpServletRequest request, String orderBy, int page) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        return new ResponseEntity<>(recipeService.selectAll(userSeq, orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/recipes/search")
    public ResponseEntity<List<RecipeDto>> search(HttpServletRequest request, String orderBy, String title, int page) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        return new ResponseEntity<>(recipeService.search(userSeq, orderBy, title, page), HttpStatus.OK);
    }

    @GetMapping("/recipes/{recipeSeq}")
    public ResponseEntity<RecipeDto> detail(@PathVariable Long recipeSeq) {
        return new ResponseEntity<>(recipeService.selectOne(recipeSeq).orElseThrow(), HttpStatus.OK);
    }
}
