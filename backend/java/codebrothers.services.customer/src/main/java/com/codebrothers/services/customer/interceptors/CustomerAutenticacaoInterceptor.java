package com.codebrothers.services.customer.interceptors;

import java.io.IOException;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
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
        if (request.getHeader("Authorization") == null) {
            response.setContentType("application/json;charset=UTF-8;");
            response.getWriter()
                    .write(new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "Autenticação inválida!","Não Autorizado!", request.getRequestURI()).toString());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());            
            log.info("Request Method: {} - URI: {} -  Local Port: {} - Auth Type: {} - QueryString: {}",
                    request.getMethod(), request.getRequestURI(), request.getLocalPort(), "Não autorizado",
                    request.getQueryString());
            
            return false;
        }
        return true;
    }
    	
    /* Implementação antiga usando o feignClient --- testes apenas
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//// Em caso de requerer algum tipo de auteticação vamos implementar nesse interceptor
        if (request.getHeader("Authorization") == null) {
            response.setContentType("application/json;charset=UTF-8;");
            response.getWriter()
                    .write(new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "Autenticação inválida!","Não Autorizado!", request.getRequestURI()).toString());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            
            
            
            return false;
        }
        else {
        	//decodificando dados de autorização
        	User u = credentialDecoder.getUserAuthorizatonData(request.getHeader("Authorization"));
        	//chamando serviço para obtenção do token
        	try {
	        	String r = authService.getAuth(u);
	        	if(r != null && !r.isEmpty()){
	        		return true;
	        	}
        	}
        	catch(Exception e) {
        		//log.error("Ocorreu um erro durante a requisição da autorização :: " + e.getMessage()); 
        		getUnauthorizedReponse(response, request);
        		return false;
        		//throw e;
        	}
        }
        return false;
    }
    */
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
