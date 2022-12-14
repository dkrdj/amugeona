package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.InbodyDto;
import com.shashashark.amugeona.model.dto.UserInfo;
import com.shashashark.amugeona.model.service.InbodyService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class InbodyController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final InbodyService inbodyService;

    @GetMapping("/inbody")
    public ResponseEntity<InbodyDto> detail(HttpServletRequest request) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        return new ResponseEntity<>(inbodyService.selectOne(loginUser.getUserSeq()).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/inbody")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody InbodyDto inbodyDto) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        inbodyDto.setUserSeq(loginUser.getUserSeq());
        inbodyService.writeInbody(inbodyDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/inbody")
    public ResponseEntity<String> modify(HttpServletRequest request, InbodyDto inbodyDto) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        if (Objects.equals(inbodyDto.getUserSeq(), loginUser.getUserSeq())) {
            inbodyService.updateInbody(inbodyDto);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/inbody")
    public ResponseEntity<String> delete(HttpServletRequest request) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        //????????? ?????? ?????? ???????????????
        inbodyService.deleteInbody(loginUser.getUserSeq());
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
