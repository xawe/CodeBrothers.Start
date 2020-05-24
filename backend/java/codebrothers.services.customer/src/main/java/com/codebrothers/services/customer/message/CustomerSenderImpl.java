package com.codebrothers.services.customer.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codebrothers.services.customer.entities.Customer;
import com.codebrothers.services.customer.infrastructure.PropertiesLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class CustomerSenderImpl implements CustomerSender {

    @Autowired
    PropertiesLoader properties;
    
    private RabbitTemplate rabbitTemplate;

    public CustomerSenderImpl(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }

// Envia uma mensagem notificando a criação do customer
    public Boolean SendCreatedMessage(Customer customer) {

        try {
            if (properties.getUseMessageBroker().equals("s")) {
                // utiliza o rabbitTemplate para envio da mensagem ao tópico ( exchange )
                rabbitTemplate.convertAndSend(
                        properties.getExchangeCustomerCreated(), 
                        properties.getExchangeSubjectCustomerCreated(), 
                        customer);
            }
        } catch (Exception e) {
            log.error("Erro ai enviar dados para mensageria: {}", e.getMessage(), e);
            return false;
        }

        return true;
    }

}
