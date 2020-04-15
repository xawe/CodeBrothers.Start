package com.codebrothers.services.customer.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codebrothers.services.customer.entities.Customer;
import com.codebrothers.services.customer.infrastructure.PropertiesLoader;


@Component
public class CustomerSenderImpl implements CustomerSender{

	@Autowired
	PropertiesLoader properties;
		
	private RabbitTemplate rabbitTemplate;	
	
	public CustomerSenderImpl(RabbitTemplate template) {
		this.rabbitTemplate = template;
	}	
	/*
	 * Envia uma mensagem notificando a criação do customer
	 */
	public Boolean SendCreatedMessage(Customer customer) {
		
		try {			
			if(properties.getUseMessageBroker().equals("s")) {
				//utiliza o rabbitTemplate para envio da mensagem ao tópico ( exchange ) "codebrothers.customer.created"
				rabbitTemplate.convertAndSend(properties.getExchangeCustomerCreated(),
						properties.getExchangeSubjectCustomerCreated(),
						customer);
			}
		}
		catch(Exception e) {
			System.out.println("Erro ao tentar enviar dados ao message broker >>> " + e.getMessage() + " >> " +e.getStackTrace());
			return false;
		}
		
		return true;
	}
	
	
}
