package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.ArticleDto;
import com.shashashark.amugeona.model.dto.ArticleUpdateParam;
import com.shashashark.amugeona.model.dto.UserInfo;
import com.shashashark.amugeona.model.service.ArticleService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@CrossOrigin
public class ArticleController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final ArticleService articleService;

    @GetMapping("/list")
    public ResponseEntity<List<ArticleDto>> list(Long boardSeq) {
        return new ResponseEntity<>(articleService.selectAll(boardSeq), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<ArticleDto> detail(Long articleSeq) {
        return new ResponseEntity<>(articleService.selectOne(articleSeq).orElseThrow(), HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modify(HttpServletRequest request, ArticleUpdateParam param) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        if (Objects.equals(param.getUserSeq(), loginUser.getUserSeq())) {
            articleService.updateArticle(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody ArticleDto articleDto) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        articleDto.setUserSeq(loginUser.getUserSeq());
        articleService.writeArticle(articleDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
