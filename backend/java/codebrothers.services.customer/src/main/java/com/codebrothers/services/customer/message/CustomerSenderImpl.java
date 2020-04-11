package com.codebrothers.services.customer.message;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.codebrothers.services.customer.entities.Customer;


@Component
public class CustomerSenderImpl implements CustomerSender{

	static final String topicExchangeName = "CustomerCreatedExchange";
	static final String queueName = "CustomerCreated";
	private RabbitTemplate rabbitTemplate;	
	
	public CustomerSenderImpl(RabbitTemplate template) {
		this.rabbitTemplate = template;
	}
	
	
	public Boolean SendCreatedMessage(Customer customer) {
		
		try {			
			//utiliza o rabbitTemplate para envio da mensagem ao tópico ( exchange ) "codebrothers.customer.created"
			rabbitTemplate.convertAndSend(topicExchangeName, "codebrothers.customer.created", customer);
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	
}
