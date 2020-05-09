package com.codebrothers.services.auth.infrastructure;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface SecurityEncoder {
	PasswordEncoder getPasswordEncoder();
}
