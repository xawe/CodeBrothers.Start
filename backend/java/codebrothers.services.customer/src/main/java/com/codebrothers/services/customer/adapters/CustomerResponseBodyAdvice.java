package com.codebrothers.services.customer.adapters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public  class CustomerResponseBodyAdvice  implements ResponseBodyAdvice<Object>{
    @Value("${spring.application.name}")
    String appName;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        
        log.info("Response Body: {}",  new Gson().toJson(body));
        
        return body;
    }
 
}
