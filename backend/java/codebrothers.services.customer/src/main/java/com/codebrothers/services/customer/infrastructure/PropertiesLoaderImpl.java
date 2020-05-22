package com.codebrothers.services.customer.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

/*
 * Classe responsável por expor as propriedades encontradas no arquivo de properties
 */
@PropertySource(ignoreResourceNotFound = false, value = "classpath:config.properties")
@Component
public class PropertiesLoaderImpl implements PropertiesLoader {			
    
    @Value("${use.messagebroker}")
    @Getter private String useMessageBroker;
    
    @Value("${customer.created.exchange}")
    @Getter private String exchangeCustomerCreated;
    
    @Value("${customer.created.exchange.subject}")
    @Getter private String exchangeSubjectCustomerCreated;
    
    @Value("${customer.created.exchange.subject.base}")
    @Getter private String exchangeSubjectCustomerBase;
  
    @Value("${customer.created.queue}")
    @Getter private String queueCustomerCreated;
    
    @Value("${service.auth")
    @Getter private String AuthServiceAddress;
    
    @Value("${auth.validate.user.endpoint")
    @Getter private String postValidadeUserEndpoint;
    
    @Value("${auth.get.token.endpoint")
    @Getter private String getTokenEndpoint;
}
