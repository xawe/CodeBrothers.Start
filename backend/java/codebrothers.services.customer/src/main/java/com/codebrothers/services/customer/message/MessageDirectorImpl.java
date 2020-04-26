package com.codebrothers.services.customer.message;

import org.springframework.beans.factory.annotation.Autowired;

import com.codebrothers.services.customer.entities.Customer;

public class MessageDirectorImpl implements MessageDirector {

	@Autowired
	CustomerSender customerSender;
	
	public void BuildAndSend(String httpVerb, Customer customer) {
		switch (httpVerb) {
		case "POST":
			customerSender.SendCreatedMessage(customer);
			break;

		default:
			break;
		}
	}
}
