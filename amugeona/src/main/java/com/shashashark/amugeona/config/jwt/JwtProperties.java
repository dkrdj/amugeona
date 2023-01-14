package com.shashashark.amugeona.config.jwt;

public interface JwtProperties {
    String SECRET = "amugeonashashashark"; // 우리 서버만 알고 있는 비밀값
    int EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}