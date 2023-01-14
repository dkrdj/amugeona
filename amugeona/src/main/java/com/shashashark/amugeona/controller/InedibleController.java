package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.config.jwt.JwtProperties;
import com.shashashark.amugeona.model.dto.InedibleDto;
import com.shashashark.amugeona.model.service.InedibleService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InedibleController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final InedibleService inedibleService;

    @GetMapping("/inedibles")
    public ResponseEntity<List<InedibleDto>> list(HttpServletRequest request) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        return new ResponseEntity<>(inedibleService.selectAll(userSeq), HttpStatus.OK);
    }

    @PostMapping("/inedible")
    public ResponseEntity<String> write(HttpServletRequest request, Long ingredientSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        InedibleDto inedibleDto = InedibleDto.builder()
                .ingredientSeq(ingredientSeq)
                .userSeq(userSeq)
                .build();
        inedibleService.writeInedible(inedibleDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/inedible")
    public ResponseEntity<String> delete(HttpServletRequest request, Long ingredientSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        inedibleService.deleteInedible(userSeq, ingredientSeq);
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }
}
