package com.codebrothers.services.customer.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codebrothers.services.customer.Interceptor.CodeBrothersAutenticacaoInterceptor;
import com.codebrothers.services.customer.Interceptor.CodeBrothersLogInterceptor;

@Configuration
public class CodeBrothersConfiguration implements WebMvcConfigurer  {
    @Autowired
    CodeBrothersLogInterceptor logInterceptor;
    @Autowired
    CodeBrothersAutenticacaoInterceptor autenticacaoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(logInterceptor);
       registry.addInterceptor(autenticacaoInterceptor);
    }
}
