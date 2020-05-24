package com.codebrothers.services.customer.controllers;

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

import com.codebrothers.services.customer.entities.Customer;
import com.codebrothers.services.customer.message.CustomerSender;
import com.codebrothers.services.customer.services.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/customers/v1", produces = "application/json;charset=UTF-8;")
public class CustomerController {
   //  Injeção da classe que faz a parte de regras que forem necessarias
	@Autowired
	CustomerService customerservice;
	
	@Autowired
	CustomerSender customerSender;

	@GetMapping()
	@ApiOperation(value = "View a list of available Customer", response = Customer.class, responseContainer = "List")
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok().body(customerservice.findAll());
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Fetch the customer specified in the path", response = Customer.class)
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(customerservice.findById(id));
	}
	
	@PostMapping
	@ApiOperation(value = "Create Customer ", response = Customer.class)
	public ResponseEntity<Customer> insert(@RequestBody @Valid Customer customer)
	{
		customer = customerservice.insert(customer);
		//Posta o novo Customer Criado no messagebroker
		customerSender.SendCreatedMessage(customer);
		// adiciona ao header uma url com ID do registro criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();		
		return ResponseEntity.created(uri).body(customer);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Delete Customer ", response = Void.class)
	public ResponseEntity<Void> delete(@PathVariable Long id)
	{
		customerservice.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update Customer ", response = Customer.class)
	public ResponseEntity<Customer> update(@PathVariable Long id,  @RequestBody Customer customer)
	{
		customer = customerservice.update(id, customer);
		return ResponseEntity.ok().body(customer);
	}
}