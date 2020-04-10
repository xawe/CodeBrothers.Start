package com.codebrothers.services.customer.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codebrothers.services.customer.Interceptor.CodeBrothersLogInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Configuration
public class CodeBrothersConfiguration implements WebMvcConfigurer  {
    @Autowired
    CodeBrothersLogInterceptor codeBrothersLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(codeBrothersLogInterceptor);
    }
    
    @Bean
    public ObjectWriter jsonObjectwriter()
    {
        return new ObjectMapper().writer().withDefaultPrettyPrinter();
    }
}
