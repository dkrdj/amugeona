package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.ArticleDto;
import com.shashashark.amugeona.model.dto.ArticleUpdateParam;
import com.shashashark.amugeona.model.dto.UserInfo;
import com.shashashark.amugeona.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ArticleTest {
    @Autowired
    ArticleController articleController;


    @Test
    @DisplayName("아티클 컨트롤러 테스트")
    void article() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createToken("loginUser",
                new UserInfo(1L, "test", "testName", "testNick", "testProfile"));

        ArticleDto articleDto = new ArticleDto().builder()
                .articleSeq(1L)
                .boardSeq(1L)
                .title("테스트 제목")
                .content("테스트 내용")
                .starRating(5.0)
                .starCnt(3)
                .build();

        ArticleUpdateParam param =
                new ArticleUpdateParam(1L, 1L, "제목 변경", "내용 변경");

        //given
        request.addHeader("access-token", token);

        //when
        articleController.write(request, articleDto);
        //then
        ArticleDto result = articleController.detail(0L).getBody();
        assert result != null;
        assertEquals(result.getUserSeq(), 0L);
        assertEquals(result.getTitle(), "같으면안되는데");
    }
}
