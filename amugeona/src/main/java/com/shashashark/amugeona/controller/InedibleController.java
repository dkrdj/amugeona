package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.InedibleDto;
import com.shashashark.amugeona.model.dto.UserInfo;
import com.shashashark.amugeona.model.service.InedibleService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/inedible")
@RequiredArgsConstructor
@CrossOrigin
public class InedibleController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final InedibleService inedibleService;

    @GetMapping("/list")
    public ResponseEntity<List<InedibleDto>> list(HttpServletRequest request) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        return new ResponseEntity<>(inedibleService.selectAll(loginUser.getUserSeq()), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody InedibleDto inedibleDto) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        inedibleDto.setUserSeq(loginUser.getUserSeq());
        inedibleService.writeInedible(inedibleDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(HttpServletRequest request, Long ingredientSeq) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        inedibleService.deleteInedible(loginUser.getUserSeq(), ingredientSeq);
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
