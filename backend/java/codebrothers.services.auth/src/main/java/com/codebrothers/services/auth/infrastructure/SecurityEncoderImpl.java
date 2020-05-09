package com.codebrothers.services.auth.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.codebrothers.services.auth.services.UserService;

@Component
public class SecurityEncoderImpl implements SecurityEncoder{

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
	      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}	
}