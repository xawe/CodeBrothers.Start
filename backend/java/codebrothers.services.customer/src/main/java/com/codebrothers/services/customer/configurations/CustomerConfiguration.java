package com.codebrothers.services.customer.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codebrothers.services.customer.interceptors.CustomerAutenticacaoInterceptor;
import com.codebrothers.services.customer.interceptors.CustomerLogInterceptor;

// Responsável po realizar o registros dos interceptors
@Configuration
public class CustomerConfiguration implements WebMvcConfigurer  {
    @Autowired
    CustomerLogInterceptor logInterceptor;
    @Autowired
    CustomerAutenticacaoInterceptor autenticacaoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(logInterceptor);
       registry.addInterceptor(autenticacaoInterceptor);
    }
}
