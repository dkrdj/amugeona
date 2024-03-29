package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.config.jwt.JwtProperties;
import com.shashashark.amugeona.model.dto.CommentUpdateParam;
import com.shashashark.amugeona.model.dto.RecipeCommentDto;
import com.shashashark.amugeona.model.service.RecipeCommentService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class RecipeCommentController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final RecipeCommentService recipeCommentService;

    @GetMapping("/recipeComments")
    public ResponseEntity<List<RecipeCommentDto>> list(Long recipeSeq, int page) {
        return new ResponseEntity<>(recipeCommentService.selectAll(recipeSeq, page), HttpStatus.OK);
    }

    @GetMapping("/recipeComments/{commentSeq}")
    public ResponseEntity<RecipeCommentDto> detail(@PathVariable Long commentSeq) {
        return new ResponseEntity<>(recipeCommentService.selectOne(commentSeq).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/recipeComments")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody RecipeCommentDto recipeCommentDto) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        recipeCommentDto.setUserSeq(userSeq);
        recipeCommentService.writeComment(recipeCommentDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/recipeComments/{commentSeq}")
    public ResponseEntity<String> modify(HttpServletRequest request, @PathVariable Long commentSeq, CommentUpdateParam param) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        if (Objects.equals(param.getUserSeq(), userSeq)) {
            param.setCommentSeq(commentSeq);
            recipeCommentService.updateComment(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/recipeComments/{commentSeq}")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable Long commentSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        RecipeCommentDto recipeCommentDto = recipeCommentService.selectOne(commentSeq).orElseThrow();
        if (Objects.equals(recipeCommentDto.getUserSeq(), userSeq)) {
            recipeCommentService.deleteComment(commentSeq);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
