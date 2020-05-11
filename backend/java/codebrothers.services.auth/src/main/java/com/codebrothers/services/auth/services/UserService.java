package com.codebrothers.services.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	//@Autowired
	//SecurityEncoder encoder;
	
    public User findUser() {
        return new User();
    }
    

    public User insert(User obj) {
    	
    	return userRepo.save(obj);
    }
    
}
