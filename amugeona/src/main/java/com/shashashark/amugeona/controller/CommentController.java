package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.config.jwt.JwtProperties;
import com.shashashark.amugeona.model.dto.CommentDto;
import com.shashashark.amugeona.model.dto.CommentUpdateParam;
import com.shashashark.amugeona.model.service.CommentService;
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
public class CommentController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Long articleSeq, int page) {
        return new ResponseEntity<>(commentService.selectAll(articleSeq, page), HttpStatus.OK);
    }

    @GetMapping("/comments/{commentSeq}")
    public ResponseEntity<CommentDto> detail(@PathVariable Long commentSeq) {
        return new ResponseEntity<>(commentService.selectOne(commentSeq).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody CommentDto commentDto) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        commentDto.setUserSeq(userSeq);
        commentService.writeComment(commentDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentSeq}")
    public ResponseEntity<String> modify(HttpServletRequest request, @PathVariable Long commentSeq, @RequestBody CommentUpdateParam param) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        if (Objects.equals(param.getUserSeq(), userSeq)) {
            param.setCommentSeq(commentSeq);
            commentService.updateComment(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentSeq}")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable Long commentSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        CommentDto comment = commentService.selectOne(commentSeq).orElseThrow();
        if (Objects.equals(userSeq, comment.getUserSeq())) {
            commentService.deleteComment(commentSeq);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

}
