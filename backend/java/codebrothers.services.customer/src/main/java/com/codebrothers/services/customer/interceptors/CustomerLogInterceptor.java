package com.codebrothers.services.customer.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomerLogInterceptor extends HandlerInterceptorAdapter {
    public Logger log = LogManager.getLogger(this);

    public CustomerLogInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       
        log.info("Request Method: {} - URI: {} -  Local Port: {} - QueryString: {}",
                request.getMethod(), request.getRequestURI(), request.getLocalPort(), request.getQueryString());
        
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) throws Exception {

        log.info("Response Status: {}", response.getStatus());
    }
}
