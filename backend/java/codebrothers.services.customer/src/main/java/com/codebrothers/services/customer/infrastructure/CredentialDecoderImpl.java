package com.codebrothers.services.customer.infrastructure;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.codebrothers.services.customer.dto.User;

import io.micrometer.core.instrument.binder.BaseUnits;

@Component
public class CredentialDecoderImpl implements CredentialDecoder{

	public User getUserAuthorizatonData(String data) {
		User u = new User();
		if(data != null && data != "") {
			String base64Credentials = data.substring("Basic".length()).trim();			
		    byte[] credDecoded = Base64Utils.decode(base64Credentials.getBytes());
		    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		    final String[] values = credentials.split(":", 2);
		    u.setName(values[0]);
		    u.setPassword(values[1]);
		}		
		return u;
	}
	
}
