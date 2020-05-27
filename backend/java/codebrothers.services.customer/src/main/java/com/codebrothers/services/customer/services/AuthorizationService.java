package com.codebrothers.services.customer.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codebrothers.services.customer.dto.User;

@FeignClient(name="codebrothers-services-auth")
//@FeignClient(value="com.codebrothers.services.auth")
public interface AuthorizationService {
	
	@RequestMapping(method = RequestMethod.POST, value =  "/api/auth/v1/login", consumes = "application/json")
	String getAuth(@RequestBody User usr);
}
