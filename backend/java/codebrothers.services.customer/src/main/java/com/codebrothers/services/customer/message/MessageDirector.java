package com.codebrothers.services.customer.message;

import com.codebrothers.services.customer.entities.Customer;

public interface MessageDirector {
	/*
	 * Método responsável por identificar o tipo de requisição http e direcionar o conteudo para o tópico desejado
	 */
	void BuildAndSend(String httpVerb, Customer customer);
}
