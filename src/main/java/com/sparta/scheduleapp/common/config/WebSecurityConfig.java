/*
package com.sparta.scheduleapp.common.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security 지원을 가능하게 함
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 비활성화
        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers("/api/auth/**").permitAll() // 로그인, 회원가입 페이지는 저근 허용
                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );
        return http.build();
    }
}*/
