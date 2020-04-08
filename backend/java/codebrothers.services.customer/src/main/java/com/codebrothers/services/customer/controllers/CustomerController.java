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
@RequestMapping(value = "/api/customers/v1")
public class CustomerController {
   //  Injeção da classe que faz a parte de regras que forem necessarias
	@Autowired
	CustomerService customerservice;

	@GetMapping()
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok().body(customerservice.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(customerservice.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Customer> insert(@RequestBody Customer customer)
	{
		customer = customerservice.insert(customer);
		// adiciona ao header uma url com ID do registro criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(customer);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id)
	{
		customerservice.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
    @PutMapping(value = "/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id,  @RequestBody Customer customer)
	{
		customer = customerservice.update(id, customer);
		return ResponseEntity.ok().body(customer);
	}
}