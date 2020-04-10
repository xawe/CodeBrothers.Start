package com.codebrothers.services.customer.Interceptor;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codebrothers.services.customer.entities.StandardError;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class CodeBrothersAutenticacaoInterceptor extends HandlerInterceptorAdapter {
    public Logger log = LogManager.getLogger(this);

    public CodeBrothersAutenticacaoInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (request.getHeader("Authorization") == null) {
            response.setContentType("application/json;charset=UTF-8;");
            response.getWriter()
                    .write(new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "Autenticação inválida!","Não Autorizado!", request.getRequestURI()).toString());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            
            log.info("Request Method: {} - URI: {} -  Local Port: {} - Auth Type: {} - QueryString: {}",
                    request.getMethod(), request.getRequestURI(), request.getLocalPort(), "Não autorizado",
                    request.getQueryString());
            
            return false;
        }

        return true;
    }
}
