package com.codebrothers.services.customer.message;

import com.codebrothers.services.customer.entities.Customer;
/*
 * Classe responsável pelo envio de informações do Customer ao MessageBroker
 */
public interface CustomerSender {
	/*
	 * Envia uma mensagem de cliente criado para o Broker, junto com o novo cliente
	 */
	Boolean SendCreatedMessage(Customer customer);

}
