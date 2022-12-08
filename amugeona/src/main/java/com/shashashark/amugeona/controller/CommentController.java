package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.CommentDto;
import com.shashashark.amugeona.model.dto.CommentUpdateParam;
import com.shashashark.amugeona.model.dto.UserInfo;
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
@RequestMapping("/comment")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final CommentService commentService;

    @GetMapping("/list")
    public ResponseEntity<List<CommentDto>> list(Long articleSeq) {
        return new ResponseEntity<>(commentService.selectAll(articleSeq), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<CommentDto> detail(Long commentSeq) {
        return new ResponseEntity<>(commentService.selectOne(commentSeq).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody CommentDto commentDto) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        commentDto.setUserSeq(loginUser.getUserSeq());
        commentService.writeComment(commentDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modify(HttpServletRequest request, CommentUpdateParam param) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        if (Objects.equals(param.getUserSeq(), loginUser.getUserSeq())) {
            commentService.updateComment(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(HttpServletRequest request, Long commentSeq) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        CommentDto comment = commentService.selectOne(commentSeq).orElseThrow();
        if (Objects.equals(loginUser.getUserSeq(), comment.getUserSeq())) {
            commentService.deleteComment(commentSeq);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

}
