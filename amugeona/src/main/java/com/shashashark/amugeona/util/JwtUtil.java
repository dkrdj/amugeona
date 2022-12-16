package com.shashashark.amugeona.util;

import com.shashashark.amugeona.model.param.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public final class JwtUtil {

    private static final String SALT = "AMUGEONA";

    //토큰 생성
    public String createToken(UserInfo user) throws UnsupportedEncodingException {
        //유효기간도 설정해서 해볼것 ㅎ
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .claim("userSeq", user.getUserSeq())
                .claim("id", user.getUserId())
                .claim("name", user.getName())
                .claim("nickname", user.getNickname())
                .claim("profileImg", user.getProfileImg())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    //토큰에서 값 추출
    public UserInfo getToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SALT.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
        return new UserInfo().builder()
                .userSeq((long) (int) claims.getBody().get("userSeq"))
                .userId((String) claims.getBody().get("id"))
                .name((String)claims.getBody().get("name"))
                .nickname((String)claims.getBody().get("nickname"))
                .profileImg((String)claims.getBody().get("profileImg"))
                .build();
}

}
