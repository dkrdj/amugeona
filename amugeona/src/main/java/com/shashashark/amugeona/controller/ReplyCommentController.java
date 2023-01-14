package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.config.jwt.JwtProperties;
import com.shashashark.amugeona.model.dto.ReplyCommentDto;
import com.shashashark.amugeona.model.dto.ReplyCommentUpdateParam;
import com.shashashark.amugeona.model.service.ReplyCommentService;
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
public class ReplyCommentController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final ReplyCommentService replyCommentService;

    @GetMapping("/replies")
    public ResponseEntity<List<ReplyCommentDto>> list(Long rootSeq, int page) {
        return new ResponseEntity<>(replyCommentService.selectAll(rootSeq, page), HttpStatus.OK);
    }

    @GetMapping("/replies/{replySeq}")
    public ResponseEntity<ReplyCommentDto> detail(@PathVariable Long replySeq) {
        return new ResponseEntity<>(replyCommentService.selectOne(replySeq).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/replies")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody ReplyCommentDto replyCommentDto) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        replyCommentDto.setUserSeq(userSeq);
        replyCommentService.writeReply(replyCommentDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/replies/{replySeq}")
    public ResponseEntity<String> modify(HttpServletRequest request, @PathVariable Long replySeq, ReplyCommentUpdateParam param) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        if (Objects.equals(param.getUserSeq(), userSeq)) {
            param.setReplySeq(replySeq);
            replyCommentService.updateReply(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/replies/{replySeq}")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable Long replySeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        ReplyCommentDto replyComment = replyCommentService.selectOne(replySeq).orElseThrow();
        if (Objects.equals(userSeq, replyComment.getUserSeq())) {
            replyCommentService.deleteReply(replySeq);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
