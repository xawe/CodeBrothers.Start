package com.codebrothers.services.customer.controllers;

import java.net.URI;
import java.util.List;

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

import com.codebrothers.services.customer.entities.Customer;
import com.codebrothers.services.customer.services.CustomerService;

@RestController
@RequestMapping(value = "/costumers")
public class CustomerController {
   //  Injeção da classe que faz a parte de regras que forem necessarias
	@Autowired
	CustomerService costumerService;

	@GetMapping()
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok().body(costumerService.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(costumerService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Customer> insert(@RequestBody Customer costumer)
	{
		costumer = costumerService.insert(costumer);
		// adiciona ao header uma url com ID do registro criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(costumer.getId()).toUri();
		return ResponseEntity.created(uri).body(costumer);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id)
	{
		costumerService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
    @PutMapping(value = "/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id,  @RequestBody Customer costumer)
	{
		costumer = costumerService.update(id, costumer);
		return ResponseEntity.ok().body(costumer);
	}
}