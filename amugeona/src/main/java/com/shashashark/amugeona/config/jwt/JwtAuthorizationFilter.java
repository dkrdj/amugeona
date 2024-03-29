package com.shashashark.amugeona.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shashashark.amugeona.config.auth.UserDetailsCustom;
import com.shashashark.amugeona.model.entity.User;
import com.shashashark.amugeona.model.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//인증
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        //header 없으면 다음체인으로 넘어감(밑의 로직 안함)
        if (!StringUtils.hasText(header) || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(JwtProperties.TOKEN_PREFIX, "");

        // 토큰 검증 여기서 예외 발생시 jwtExceptionFilter에서 처리
        Long userSeq = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("userSeq").asLong();

        if (userSeq != null) {
            User user = userRepository.findById(userSeq).orElseThrow();

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            UserDetailsCustom userDetailsCustom = new UserDetailsCustom(user);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetailsCustom,
                            null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아님
                            userDetailsCustom.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

}