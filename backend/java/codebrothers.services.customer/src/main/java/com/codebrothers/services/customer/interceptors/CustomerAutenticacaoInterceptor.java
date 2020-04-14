package com.codebrothers.services.customer.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class CustomerAutenticacaoInterceptor extends HandlerInterceptorAdapter {
    
    public CustomerAutenticacaoInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//// Em caso de requerer algum tipo de auteticação vamos implementar nesse interceptor
//        if (request.getHeader("Authorization") == null) {
//            response.setContentType("application/json;charset=UTF-8;");
//            response.getWriter()
//                    .write(new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "Autenticação inválida!","Não Autorizado!", request.getRequestURI()).toString());
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            
//            log.info("Request Method: {} - URI: {} -  Local Port: {} - Auth Type: {} - QueryString: {}",
//                    request.getMethod(), request.getRequestURI(), request.getLocalPort(), "Não autorizado",
//                    request.getQueryString());
//            
//            return false;
//        }
        return true;
    }
}
