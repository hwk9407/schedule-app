package com.sparta.scheduleapp.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
//@Component
@Order(1)
public class LogginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        // 전처리
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String url = httpServletRequest.getRequestURI();
        log.info(url);

        chain.doFilter(req, res);

        // 후처리
    }
}
