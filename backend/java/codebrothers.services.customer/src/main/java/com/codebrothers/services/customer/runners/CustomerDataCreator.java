package com.codebrothers.services.customer.runners;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codebrothers.services.customer.entities.Customer;
import com.codebrothers.services.customer.services.CustomerService;
import com.codebrothers.services.customer.runners.Helpers;

@Component
public class CustomerDataCreator implements CommandLineRunner{

	@Autowired
	CustomerService customerservice;
	
	public CustomerDataCreator() {
		
	}																																																											
	
	@Override																															
	public void run(String... args) throws Exception {
		//createCustomers(10);
	}
	
	private void createCustomers(Integer qtd)
	{
		System.out.println("Iniciando a criação de registros de Customer");				
		for (int i = 0; i < qtd; i++) {
			Customer c = new Customer();
			c.setNome(new Helpers().getRandomName());
			c.setSobrenome(new Helpers().getRandomSecondName(2));	
			c.setDataNascimento(new Helpers().getRandomDate());
			c.setCpf(new Helpers().getRandomCPF());
			c.setRg(new Helpers().getRandomRG());
			c.setCriadoEm(LocalDateTime.now());
			c.setAtivo(true);
			// Descomentar a linha abaixoi quando desejar criar o registro
			//customerservice.insert(c);			
			System.out.println("Foram criados " + qtd + " clientes");
		}
	}
}
