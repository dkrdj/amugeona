package com.shashashark.amugeona.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shashashark.amugeona.config.jwt.JwtProperties;

public class JwtUtil {
    public Long getUserSeq(String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("userSeq").asLong();
    }
}
