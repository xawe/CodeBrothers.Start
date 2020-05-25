package com.codebrothers.services.customer.interceptors;

import java.io.IOException;
import java.time.Instant;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codebrothers.services.customer.dto.User;
import com.codebrothers.services.customer.entities.StandardError;
import com.codebrothers.services.customer.infrastructure.CredentialDecoder;
import com.codebrothers.services.customer.services.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class CustomerAutenticacaoInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private CredentialDecoder credentialDecoder;
	
    //Injeção do serviço que utiliza o feigClient para recuperar o token de autorização
    //@Autowired
    //private AuthorizationService authService;
	
    public CustomerAutenticacaoInterceptor() {
        super();
    }
    
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
// Em caso de requerer algum tipo de auteticação vamos implementar nesse interceptor
        if (request.getHeader("Authorization") == null && !request.getHeader("Authorization").contains("Bearer")) {
        	getUnauthorizedReponse(response, request);            
            return false;
        }
        //String auth = request.getHeader("Authorization");                
        return true;
    }  
    
    
    
    private HttpServletResponse getUnauthorizedReponse(HttpServletResponse response, HttpServletRequest request) throws IOException {
    	response.setContentType("application/json;charset=UTF-8;");
        response.getWriter()
                .write(new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "Autenticação inválida!","Não Autorizado!", request.getRequestURI()).toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        
        log.info("Request Method: {} - URI: {} -  Local Port: {} - Auth Type: {} - QueryString: {}",
                request.getMethod(), request.getRequestURI(), request.getLocalPort(), "Não autorizado",
                request.getQueryString());
        return response;
    }
}
