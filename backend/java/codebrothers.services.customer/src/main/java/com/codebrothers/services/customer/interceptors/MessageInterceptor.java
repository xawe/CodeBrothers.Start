package com.codebrothers.services.customer.interceptors;

import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codebrothers.services.customer.entities.Customer;
import com.codebrothers.services.customer.message.MessageDirector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MessageInterceptor extends HandlerInterceptorAdapter{

	@ControllerAdvice
	public class GlobalControllerAdvice {
	 
	    @ModelAttribute("customer") //use this name in your jsp etc to reference it
	    public Customer mySimpleModelAttribute() {
	        return new Customer();
	    }
	}
	
	@Autowired
	MessageDirector msgDirector;
	
	public MessageInterceptor() {
		super();
	}
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
       Object handler, Exception exception) throws Exception {
    	
    	Integer a = 1;    	
    	RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
    	HttpServletRequest req = ((ServletRequestAttributes) reqAttr).getRequest();
    	//Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
    	//String requestStr = IOUtils.toString(request.getInputStream());
    	//msgDirector.BuildAndSend(request.getMethod(), null);
    	//customerSender.SendCreatedMessage(customer);
    }

}
