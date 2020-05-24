package com.codebrothers.services.auth.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codebrothers.services.auth.interceptors.AuthInterceptor;

// Responsável po realizar o registros dos interceptors
@Configuration
public class AuthConfiguration implements WebMvcConfigurer  {
    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(authInterceptor);
    }
}
