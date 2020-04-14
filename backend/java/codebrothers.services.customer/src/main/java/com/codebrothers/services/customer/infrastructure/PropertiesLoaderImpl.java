package com.codebrothers.services.customer.infrastructure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

/*
 * Classe responsável por expor as propriedades encontradas no arquivo de properties
 */
@Component
public class PropertiesLoaderImpl implements PropertiesLoader {			

	public PropertiesLoaderImpl() throws IOException {
		this.properties = this.LoadProperties();
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	private Properties properties;
	
	/*
	 * Carrega as propriedades do arquivo config.properties
	 */
	public Properties LoadProperties() throws IOException {
		Properties prop = new Properties();
		InputStream inputStream;
		try {			
			String propFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if(inputStream != null) {
				prop.load(inputStream);				
			}else {
				throw new FileNotFoundException("o arquivo " + propFileName + " não foi encontrado");
			}							
		}
		catch(Exception e) {
			System.out.println("Ocorreu um erro ao tentar obter as propriedades >> " + e.getMessage());
		}		
		return prop;
	}
	
	
	/***        propriedades expostas no arquivo 		***/
	
	private String useMessageBroker;

	public String getUseMessageBroker() {
		return this.properties.getProperty("use.messagebroker");		
	}
	
	public String getExchangeCustomerCreated() {
		return this.properties.getProperty("customer.created.exchange");		
	}
	
	public String getExchangeSubjectCustomerCreated() {
		return this.properties.getProperty("customer.created.exchange.subject");		
	}
	
	public String getQueueCustomerCreated() {
		return this.properties.getProperty("customer.created.queue");		
	}
	
	public String getExchangeSubjectCustomerBase() {
		return this.properties.getProperty("customer.created.exchange.subject.base");		
	}	

}
