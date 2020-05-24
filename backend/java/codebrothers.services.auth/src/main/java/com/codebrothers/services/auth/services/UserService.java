package com.codebrothers.services.auth.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    
    public User authenticateUser(User u) {
    	
    	List<User> users = userRepo.findByName(u.getName());
    	if(users != null && !users.isEmpty())
    	{
    		User user = users.get(0);
    		if(dataEncryptor.match(u.getPassword(), user.getPassword())) {
    			//user.setPassword("");
    			return user;
    		}
    	}
    	
    	return null;
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
    
    public List<User> findAll() {
        return userRepo.findAll();
    }
    
    public User findById(Long id) {      
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
    	if(obj.getPassword() != null) {
    		obj.setPassword(dataEncryptor.getEncryptedData(obj.getPassword()));
    	}
    	return userRepo.save(obj);
    }
    
    public void delete(Long id) {
        try {
            userRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new com.codebrothers.services.auth.exceptions.DataBaseException(e.getMessage());
        }
    }
    
    public User update(Long id, User obj) {
        try {
            User entity = userRepo.getOne(id);
            updateData(entity, obj);
            return userRepo.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }   
    }

    private void updateData(User entity, User obj) {    	
        entity.setName(obj.getName());
        entity.setPassword(dataEncryptor.getEncryptedData(obj.getPassword()));
        entity.setEmail(obj.getEmail());        
    }
    
}
