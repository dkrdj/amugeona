package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.ArticleDto;
import com.shashashark.amugeona.model.dto.ArticleLikeDto;
import com.shashashark.amugeona.model.param.ArticleUpdateParam;
import com.shashashark.amugeona.model.param.UserInfo;
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
@RequestMapping("/article")
@RequiredArgsConstructor
@CrossOrigin
public class ArticleController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtUtil jwtUtil;
    private final ArticleService articleService;
    private final ArticleLikeService articleLikeService;

    @GetMapping("/list")
    public ResponseEntity<List<ArticleDto>> list(Long boardSeq, String orderBy, int page) {
        return new ResponseEntity<>(articleService.selectAll(boardSeq, orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<ArticleDto>> listAll(String orderBy, int page) {
        return new ResponseEntity<>(articleService.selectAllList(orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<ArticleDto> detail(Long articleSeq) {
        return new ResponseEntity<>(articleService.selectOne(articleSeq).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/search-title")
    public ResponseEntity<List<ArticleDto>> searchTitle(Long boardSeq, String title, String orderBy, int page) {
        return new ResponseEntity<>(articleService.searchTitle(boardSeq, title, orderBy, page), HttpStatus.OK);
    }

    @GetMapping("/search-content")
    public ResponseEntity<List<ArticleDto>> searchContent(Long boardSeq, String content, String orderBy, int page) {
        return new ResponseEntity<>(articleService.searchContent(boardSeq, content, orderBy, page), HttpStatus.OK);
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

        //로그인 유저가 없으면 FAIL
        if (loginUser == null) {
            status = HttpStatus.UNAUTHORIZED;
            result = FAIL;
        }
        //로그인이 되어있고 좋아요를 안눌렀을 경우 추가
        else if (!articleLikeService.findOne(loginUser.getUserSeq(), articleSeq)) {
            ArticleLikeDto articleLikeDto = ArticleLikeDto.builder()
                    .userSeq(loginUser.getUserSeq())
                    .articleSeq(articleSeq)
                    .build();
            articleLikeService.writeLike(articleLikeDto);
            articleService.updateLike(articleSeq);
            status = HttpStatus.OK;
            result = SUCCESS;
        }
        //좋아요를 눌렀었는데 다시 누른 경우에는 좋아요 취소
        else {
            articleLikeService.deleteLike(loginUser.getUserSeq(), articleSeq);
            status = HttpStatus.OK;
            result = SUCCESS;
        }
        return new ResponseEntity<>(result, status);
    }
}
