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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe/{recipeSeq}")
public class StarController {
    private static final String SUCCESS = "success";
    private final JwtUtil jwtUtil;
    private final StarService starService;

    @GetMapping("/stars")
    public ResponseEntity<List<StarDto>> list(@PathVariable Long recipeSeq) {
        return new ResponseEntity<>(starService.selectAll(recipeSeq), HttpStatus.OK);
    }

    //작성할 때 article도 업데이트 진행해줘야함
    @PostMapping("/stars")
    public ResponseEntity<String> write(HttpServletRequest request, @PathVariable Long recipeSeq, @RequestBody StarDto starDto) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        starDto.setUserSeq(userSeq);
        starDto.setRecipeSeq(recipeSeq);
        starService.writeStar(starDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/stars")
    public ResponseEntity<String> modify(HttpServletRequest request, @PathVariable Long recipeSeq, @RequestBody StarUpdateParam param) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        param.setUserSeq(userSeq);
        param.setRecipeSeq(recipeSeq);
        starService.updateStar(param);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/stars")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable Long recipeSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        starService.deleteStar(userSeq, recipeSeq);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }


}
