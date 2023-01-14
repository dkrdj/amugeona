package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.config.jwt.JwtProperties;
import com.shashashark.amugeona.model.dto.StarDto;
import com.shashashark.amugeona.model.dto.StarUpdateParam;
import com.shashashark.amugeona.model.service.StarService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class StarController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final StarService starService;

    @GetMapping("/star/{recipeSeq}")
    public ResponseEntity<StarDto> detail(HttpServletRequest request, @PathVariable Long recipeSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        return new ResponseEntity<>(starService.selectOne(userSeq, recipeSeq).orElseThrow(), HttpStatus.OK);
    }

    //작성할 때 article도 업데이트 진행해줘야함
    @PostMapping("/star")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody StarDto starDto) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        starDto.setUserSeq(userSeq);
        starService.writeStar(starDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/star")
    public ResponseEntity<String> modify(HttpServletRequest request, @RequestBody StarUpdateParam param) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        if (Objects.equals(userSeq, param.getUserSeq())) {
            starService.updateStar(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/star")
    public ResponseEntity<String> delete(HttpServletRequest request, Long recipeSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        StarDto starDto = starService.selectOne(userSeq, recipeSeq).orElseThrow();
        if (Objects.equals(userSeq, starDto.getUserSeq())) {
            starService.deleteStar(starDto.getStarSeq());
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }


}
