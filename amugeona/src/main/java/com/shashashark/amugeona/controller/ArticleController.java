package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.ArticleDto;
import com.shashashark.amugeona.model.param.ArticleUpdateParam;
import com.shashashark.amugeona.model.param.UserInfo;
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
    public ResponseEntity<List<ArticleDto>> list(Long boardSeq, String orderBy, int page) {
        return new ResponseEntity<>(articleService.selectAll(boardSeq, orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<ArticleDto> detail(Long articleSeq) {
        return new ResponseEntity<>(articleService.selectOne(articleSeq).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/search-title")
    public ResponseEntity<List<ArticleDto>> searchTitle(String title, String orderBy, int page) {
        return new ResponseEntity<>(articleService.searchTitle(title, orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/search-content")
    public ResponseEntity<List<ArticleDto>> searchContent(String content, String orderBy, int page) {
        return new ResponseEntity<>(articleService.searchContent(content, orderBy, page), HttpStatus.OK);
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
        System.out.println(request.getHeader(HEADER_AUTH));
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        articleDto.setUserSeq(loginUser.getUserSeq());
        articleService.writeArticle(articleDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(HttpServletRequest request, Long articleSeq) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        ArticleDto article = articleService.selectOne(articleSeq).orElseThrow();
        if (Objects.equals(loginUser.getUserSeq(), article.getUserSeq())) {
            articleService.deleteArticle(articleSeq);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> like(HttpServletRequest request, Long articleSeq) {
        UserInfo loginUser = jwtUtil.getToken(request.getHeader(HEADER_AUTH));
        String result;
        HttpStatus status;
        if (loginUser != null) {
            articleService.updateLike(articleSeq);
            status = HttpStatus.OK;
            result = SUCCESS;
        } else {
            status = HttpStatus.UNAUTHORIZED;
            result = FAIL;
        }
        return new ResponseEntity<>(result, status);
    }
}
