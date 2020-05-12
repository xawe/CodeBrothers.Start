package com.codebrothers.services.auth.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codebrothers.services.auth.entities.User;
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
    @ApiOperation(value = "Busca de usuário por nome e e-mail", response = Object.class, responseContainer = "List")
    public ResponseEntity<?> findUser(@PathVariable String name, @PathVariable String email) {    	    
        return ResponseEntity.ok().body(service.findUserByNameAndEmail(name, email));
    }
    
	@PostMapping("/findByNameEmail")
    @ApiOperation(value = "Busca de usuários por nome e email usando o Body do post", response = Object.class, responseContainer = "List")
    public ResponseEntity<?> login(@RequestBody @Valid User user) {    	    
        return ResponseEntity.ok().body(service.findUserByNameAndEmail(user));
    }
	
	@GetMapping()
	@ApiOperation(value = "View a list of available User", response = User.class, responseContainer = "List")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Fetch the User specified in the path", response = User.class)
	public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Delete User ", response = Void.class)
	public ResponseEntity<Void> delete(@PathVariable Long id)
	{
		service.delete(id);		
		return ResponseEntity.ok().build();
	}
	
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update User", response = User.class)
	public ResponseEntity<User> update(@PathVariable Long id,  @RequestBody User user)
	{
		user = service.update(id, user);
		return ResponseEntity.ok().body(user);
	}
	
	
}
