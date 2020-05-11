package com.codebrothers.services.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.repositories.UserRepository;
import com.codebrothers.services.auth.security.DataEncryptor;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DataEncryptor dataEncryptor; 
	
    public User findUser() {
        return new User();
    }
    

    public User insert(User obj) {
    	if(obj.getPassword() != null) {
    		obj.setPassword(dataEncryptor.getEncryptedData(obj.getPassword()));
    	}
    	return userRepo.save(obj);
    }
    
}
