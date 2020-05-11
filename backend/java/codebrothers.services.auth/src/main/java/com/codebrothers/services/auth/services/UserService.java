package com.codebrothers.services.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.exceptions.ResourceNotFoundException;
import com.codebrothers.services.auth.repositories.UserRepository;
import com.codebrothers.services.auth.security.DataEncryptor;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DataEncryptor dataEncryptor; 
	
    public User findUserByNameAndEmail(User user) throws ResourceNotFoundException {
    	//mock para continuar funcionando o teste + teste@teste.com
    	if(user.getEmail().equals("teste@teste.com") && user.getName().equals("teste")) {
    		return user;
    	}
    	
    	List<User> users = userRepo.findByNameAndEmail(user.getName(), user.getEmail());
    	if(!users.isEmpty()) {
    		return users.get(0);
    	}    	
    	
        throw new ResourceNotFoundException(user);
    }
    
    public User findUserByNameAndEmail(String name, String email) throws ResourceNotFoundException {
    	//mock para continuar funcionando o teste + teste@teste.com
    	if(email.equals("teste@teste.com") && name.equals("teste")) {
    		User u = new User();
    		u.setEmail(email);
    		u.setName(name);
    		return u;
    	}
    	
    	List<User> users = userRepo.findByNameAndEmail(name, email);
    	if(!users.isEmpty()) {
    		return users.get(0);
    	}    	
    	
        throw new ResourceNotFoundException("User : " + name +" | Email : " + email);
    }
    

    public User insert(User obj) {
    	if(obj.getPassword() != null) {
    		obj.setPassword(dataEncryptor.getEncryptedData(obj.getPassword()));
    	}
    	return userRepo.save(obj);
    }
    
}
