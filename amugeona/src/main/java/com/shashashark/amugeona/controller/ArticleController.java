package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.config.jwt.JwtProperties;
import com.shashashark.amugeona.model.dto.ArticleDto;
import com.shashashark.amugeona.model.dto.ArticleLikeDto;
import com.shashashark.amugeona.model.dto.ArticleSearchParam;
import com.shashashark.amugeona.model.dto.ArticleUpdateParam;
import com.shashashark.amugeona.model.service.ArticleLikeService;
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
@RequiredArgsConstructor
@CrossOrigin
public class ArticleController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final ArticleService articleService;
    private final ArticleLikeService articleLikeService;

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> list(Long boardSeq, String orderBy, int page) {
        return new ResponseEntity<>(articleService.selectAll(boardSeq, orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/article/{articleSeq}")
    public ResponseEntity<ArticleDto> detail(@PathVariable Long articleSeq) {
        return new ResponseEntity<>(articleService.selectOne(articleSeq).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleDto>> searchTitle(@RequestBody ArticleSearchParam param) {
        return new ResponseEntity<>(articleService.search(param), HttpStatus.OK);
    }

    @PutMapping("/article")
    public ResponseEntity<String> modify(HttpServletRequest request, ArticleUpdateParam param) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        if (Objects.equals(param.getUserSeq(), userSeq)) {
            articleService.updateArticle(param);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @PostMapping("/article")
    public ResponseEntity<String> write(HttpServletRequest request, @RequestBody ArticleDto articleDto) {
        System.out.println(request.getHeader(HEADER_AUTH));
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        articleDto.setUserSeq(userSeq);
        articleService.writeArticle(articleDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/article")
    public ResponseEntity<String> delete(HttpServletRequest request, Long articleSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        ArticleDto article = articleService.selectOne(articleSeq).orElseThrow();
        if (Objects.equals(userSeq, article.getUserSeq())) {
            articleService.deleteArticle(articleSeq);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> like(HttpServletRequest request, Long articleSeq) {
        Long userSeq = jwtUtil.getUserSeq(request.getHeader(JwtProperties.HEADER_STRING));
        //로그인이 되어있고 좋아요를 안눌렀을 경우 추가
        if (!articleLikeService.findOne(userSeq, articleSeq)) {
            ArticleLikeDto articleLikeDto = ArticleLikeDto.builder()
                    .userSeq(userSeq)
                    .articleSeq(articleSeq)
                    .build();
            articleLikeService.writeLike(articleLikeDto);
            articleService.updateLike(articleSeq);
        }
        //좋아요를 눌렀었는데 다시 누른 경우에는 좋아요 취소
        else {
            articleLikeService.deleteLike(userSeq, articleSeq);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
