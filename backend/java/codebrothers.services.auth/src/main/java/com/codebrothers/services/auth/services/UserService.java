package com.codebrothers.services.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.infrastructure.SecurityEncoder;
import com.codebrothers.services.auth.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	SecurityEncoder encoder;
	
    public User findUser() {
        return new User();
    }
    
    // Necessário autorizar o método, do contrário não haverá permissão para uso do encoder
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User insert(User obj) {
    	if(!obj.equals(null)) {
    		if(!obj.getPassword().equals(null)) {
    			//criptografando o password usando bcCrypt
    			obj.setPassword(encoder
    					.getPasswordEncoder()
    					.encode(obj.getPassword()));
    		}
    	}
    	return userRepo.save(obj);
    }
    
}
