package com.codebrothers.services.customer.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codebrothers.services.customer.dto.UserCredential;

@FeignClient(name="authService", url="http://localhost:8082")
public interface AuthorizationService {
	
	@RequestMapping(method = RequestMethod.POST, value =  "/api/auth/v1/login", consumes = "application/json")
	String getAuth(@RequestBody UserCredential usr);
}
