package com.codebrothers.services.customer.infrastructure.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codebrothers.services.customer.dto.User;
import com.codebrothers.services.customer.infrastructure.CredentialDecoderImpl;

class CredentialDecoderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	private String encodedAdmin = "Basic YWRtaW46YWRtaW4=";

	@Test
	void GetUserFromBase64Test() {
		CredentialDecoderImpl cd = new CredentialDecoderImpl();
		User u =cd.getUserAuthorizatonData(encodedAdmin);
		assertEquals(u.getName(), "admin");
		assertEquals(u.getPassword(), "admin");		
	}
}
