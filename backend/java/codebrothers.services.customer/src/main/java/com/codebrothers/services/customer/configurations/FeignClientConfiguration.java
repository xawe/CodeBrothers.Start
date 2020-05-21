package com.codebrothers.services.customer.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

//@Configuration
public class FeignClientConfiguration {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("teste", "teste@teste.com");
   }	
}
