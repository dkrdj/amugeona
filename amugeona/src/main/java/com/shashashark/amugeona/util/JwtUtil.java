package com.shashashark.amugeona.util;

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

    //유효성 검사
    public void valid(String token) throws Exception {
        Jwts.parser().setSigningKey(SALT.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
    }

    //토큰에서 값 추출
//    public JwtUser getToken(String token) throws Exception {
//        Jws<Claims> claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
//        String id = (String) claims.getBody().get("id");
//        String userName = (String) claims.getBody().get("name");
//        String userNickName = (String) claims.getBody().get("nickName");
//        String profile_img = (String) claims.getBody().get("profile");
//        JwtUser loginUser = new JwtUser(id, userName, userNickName, profile_img);
//        return loginUser;
//    }

}
