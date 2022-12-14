package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.ReplyCommentDto;
import com.shashashark.amugeona.model.dto.ReplyCommentUpdateParam;
import com.shashashark.amugeona.model.dto.UserInfo;
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
@CrossOrigin
public class ReplyCommentController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final ReplyCommentService replyCommentService;

    @GetMapping("/replies")
    public ResponseEntity<List<ReplyCommentDto>> list(Long rootSeq, int page) {
        return new ResponseEntity<>(replyCommentService.selectAll(rootSeq, page), HttpStatus.OK);
    }

    @GetMapping("/reply/{replySeq}")
    public ResponseEntity<ReplyCommentDto> detail(@PathVariable Long replySeq) {
        return new ResponseEntity<>(replyCommentService.selectOne(replySeq).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/reply")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody ReplyCommentDto replyCommentDto) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        replyCommentDto.setUserSeq(loginUser.getUserSeq());
        replyCommentService.writeReply(replyCommentDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/reply")
    public ResponseEntity<String> modify(HttpServletRequest request, ReplyCommentUpdateParam param) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        if (Objects.equals(param.getUserSeq(), loginUser.getUserSeq())) {
            replyCommentService.updateReply(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/reply")
    public ResponseEntity<String> delete(HttpServletRequest request, Long replySeq) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        ReplyCommentDto replyComment = replyCommentService.selectOne(replySeq).orElseThrow();
        if (Objects.equals(loginUser.getUserSeq(), replyComment.getUserSeq())) {
            replyCommentService.deleteReply(replySeq);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
