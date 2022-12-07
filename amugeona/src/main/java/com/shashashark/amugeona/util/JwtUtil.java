package com.shashashark.amugeona.util;

import com.shashashark.amugeona.model.dto.UserInfo;
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

    private static final String SALT = "SSAFIT";

    //토큰 생성
    public String createToken(String claimId, Object data) throws UnsupportedEncodingException {
        //유효기간도 설정해서 해볼것 ㅎ
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .claim(claimId, data)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    //토큰에서 값 추출
    public String getToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SALT.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
        return (String) claims.getBody().get("id");
    }

}
