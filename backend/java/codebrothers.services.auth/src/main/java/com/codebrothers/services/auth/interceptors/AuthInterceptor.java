package com.codebrothers.services.auth.interceptors;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codebrothers.services.auth.entities.StandardError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private Set<String> urlsByPass = new HashSet<>(); 
    
    public AuthInterceptor() {
        super();
        urlsByPass.add("/api/auth/v1/login");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Em caso de requerer algum tipo de auteticação vamos implementar nesse interceptor
        if (request.getHeader("Authorization") == null && !urlsByPass.contains(request.getRequestURI())) {
            response.setContentType("application/json;charset=UTF-8;");
            response.getWriter().write(new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "Token inválido!","Não Autorizado!", request.getRequestURI()).toString());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            
            log.info("Request Method: {} - URI: {} -  Local Port: {} - Auth Type: {}",
                    request.getMethod(), request.getRequestURI(), request.getLocalPort(), "Não autorizado");
            
            return false;
        }
        return true;
    }
}
