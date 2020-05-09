package com.codebrothers.services.auth;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codebrothers.services.auth.infrastructure.SecurityEncoder;
import com.codebrothers.services.auth.infrastructure.SecurityEncoderImpl;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void encryptAdmimPassTest() {
		String admimPass = "admin";
		com.codebrothers.services.auth.infrastructure.SecurityEncoder se = new SecurityEncoderImpl();
		PasswordEncoder pe = se.getPasswordEncoder();
		String encodedPass = pe.encode(admimPass);
		System.out.println("Pass");
		System.out.println(encodedPass);
		
		System.out.println("------------------");
		System.out.println(se.getPasswordEncoder().encode(admimPass));
		System.out.println(se.getPasswordEncoder().encode(admimPass));
		System.out.println(se.getPasswordEncoder().encode(admimPass));
		System.out.println(se.getPasswordEncoder().encode(admimPass));
		
		assertTrue(pe.matches(admimPass, "$2a$10$YwRPdZOBAqr/fIvuNR/iOOv7xYbiMcSGeV4H68rlhI4jlXa6jd8Se"));		
		
	}

}
