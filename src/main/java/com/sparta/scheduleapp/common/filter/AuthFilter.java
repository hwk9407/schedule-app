package com.sparta.scheduleapp.common.filter;


import com.sparta.scheduleapp.common.exception.TokenNotFoundException;
import com.sparta.scheduleapp.common.jwt.JwtUtil;
import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.startsWith("/api/auth") || httpServletRequest.getMethod().equals("GET"))
        ) {
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest);



            if (!StringUtils.hasText(tokenValue)) { // 토큰이 존재하지 않다면 예외 처리
                throw new TokenNotFoundException("토큰을 찾을 수 없습니다.");
            }



            // 토큰 검증
            if (!jwtUtil.validateToken(tokenValue)) {
                throw new IllegalArgumentException("토큰 검증을 실패하였습니다.");
            }

            // 토큰에서 사용자 정보 가져오기
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

            User user = userRepository.findById(Long.valueOf(info.getSubject())).orElseThrow(() ->
                    new NullPointerException("유저를 찾을 수 없습니다.")
            );

            request.setAttribute("userId", user.getUserId());
            request.setAttribute("userName", user.getUserName());
            request.setAttribute("role", user.getRole());
            chain.doFilter(request, response); // 다음 Filter 로 이동
        }
    }

}
