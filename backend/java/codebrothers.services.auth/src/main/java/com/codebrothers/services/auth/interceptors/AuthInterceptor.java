package com.codebrothers.services.auth.interceptors;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codebrothers.services.auth.entities.StandardError;
import com.codebrothers.services.auth.services.TokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private Set<String> urlsByPass = new HashSet<>();

    @Autowired
    TokenService jwtToken;

    public AuthInterceptor() {
        super();
        urlsByPass.add("/api/auth/v1/login");
        urlsByPass.add("/api/user/v1/validate");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Em caso de requerer algum tipo de auteticação vamos implementar nesse
        // interceptor
        
        if(urlsByPass.contains(request.getRequestURI()))
            return true;
        
        if (request.getHeader("Authorization") == null) {
            responseInvalidJwt(request, response, HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        try {
            if (!jwtToken.validateToken(request.getHeader("Authorization"))) {
                responseInvalidJwt(request, response, HttpStatus.UNAUTHORIZED.value());
                return false;
            }
        } catch (Exception e) {
            responseInvalidJwt(request, response, HttpStatus.BAD_REQUEST.value());
            log.error("AuthInterceptor - Error: {}", e.getMessage());
            return false;
        }

        return true;
    }

    private void responseInvalidJwt(HttpServletRequest request, HttpServletResponse response, int httpStatus) throws IOException {
        response.setContentType("application/json;charset=UTF-8;");
        response.getWriter().write(new StandardError(Instant.now(), httpStatus, "Token inválido!", "Token inválido!", request.getRequestURI()).toString());
        response.setStatus(httpStatus);
        
        log.info("Request Method: {} - URI: {} -  Local Port: {} - HTTP Status: {}", request.getMethod(), request.getRequestURI(), request.getLocalPort(), "Não autorizado");
    }
}
