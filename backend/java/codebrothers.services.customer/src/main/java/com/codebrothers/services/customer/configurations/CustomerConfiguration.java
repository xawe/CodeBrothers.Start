package com.codebrothers.services.customer.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codebrothers.services.customer.interceptors.CustomerAutenticacaoInterceptor;
import com.codebrothers.services.customer.interceptors.CustomerLogInterceptor;
import com.codebrothers.services.customer.interceptors.MessageInterceptor;
import com.codebrothers.services.customer.message.MessageDirector;
import com.codebrothers.services.customer.message.MessageDirectorImpl;

// Responsável po realizar o registros dos interceptors
@Configuration
public class CustomerConfiguration implements WebMvcConfigurer  {
    @Autowired
    CustomerLogInterceptor logInterceptor;
    @Autowired
    CustomerAutenticacaoInterceptor autenticacaoInterceptor;
    @Autowired
    MessageInterceptor messageInterceptor;
    
    @Bean
    MessageDirector messageDirector() {
    	return new MessageDirectorImpl();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(logInterceptor);
       registry.addInterceptor(autenticacaoInterceptor);
       registry.addInterceptor(messageInterceptor);
    }
}
