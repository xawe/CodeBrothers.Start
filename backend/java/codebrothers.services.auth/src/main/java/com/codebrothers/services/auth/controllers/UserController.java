package com.codebrothers.services.auth.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.services.AuthService;
import com.codebrothers.services.auth.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/user/v1/", produces = "application/json;charset=UTF-8;")
public class UserController {

	@Autowired
	UserService service;
	   	
	
	@PostMapping()
    @ApiOperation(value = "Create user ", response = User.class)
	public ResponseEntity<User> insert(@RequestBody @Valid User user)
	{		
		user = service.insert(user);
		//		
		// adiciona ao header uma url com ID do registro criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();		
		return ResponseEntity.created(uri).body(user);
	}
		
    @GetMapping("/{name}/{email}")
    @ApiOperation(value = "API for authentication", response = Object.class, responseContainer = "List")
    public ResponseEntity<?> findUser(@PathVariable String name, @PathVariable String email) {    	    
        return ResponseEntity.ok().body(service.findUserByNameAndEmail(name, email));
    }
    
    //@GetMapping()
	@PostMapping("/findByNameEmail")
    @ApiOperation(value = "API for authentication", response = Object.class, responseContainer = "List")
    public ResponseEntity<?> login(@RequestBody @Valid User user) {    	    
        return ResponseEntity.ok().body(service.findUserByNameAndEmail(user));
    }
}
