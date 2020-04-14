package com.codebrothers.services.customer.infrastructure;


public interface PropertiesLoader {
	/*
	 * FeatureToggle para definir o uso de comunicação via messageria
	 */
	public String getUseMessageBroker();
	
	/*
	 * Propriedade indicando o nome da exchange para enviar a mensagem de customer criado
	 */
	public String getExchangeCustomerCreated();
	
	/*
	 * Propriedade que indica o nome do assunto a ser enviado na exchange, permitindo filtros de
	 * binding
	 */
	public String getExchangeSubjectCustomerCreated();
	
	/*
	 * Propriedade indicando o nome da fila a ser criada
	 */
	public String getQueueCustomerCreated();
	
	/*
	 * propriedade indicando a base do assunto para ser usado nos filtros da exchange
	 */
	public String getExchangeSubjectCustomerBase();
}
