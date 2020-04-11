package com.codebrothers.services.customer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@SpringBootApplication
public class Application {

	static final String topicExchangeName = "CustomerCreatedExchange";
	static final String queueName = "CustomerCreated";
	
	/*
	 * retorna um instacia da fila
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, false);		
	}
	
	/*
	 * Retorna uma instancia do topico ( exchange )
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}
	
	/*
	 *Cria a ligação entre o topico e a fila quando o assunto do do topico contiver codebrothers.customer. 
	 */
	/*@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("codebrothers.customer.#");	
	}*/
	/*
	@Bean
	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	      MessageListenerAdapter listenerAdapter) {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    container.setQueueNames(queueName);
	    container.setMessageListener(listenerAdapter);
	    return container;
	  }
*/
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
