package com.codebrothers.services.customer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.codebrothers.services.customer.infrastructure.PropertiesLoader;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class Application {

    @Autowired
    PropertiesLoader properties;

    /*
     * retorna um instacia da fila
     */
    @Bean
    Queue queue() {
        return new Queue(properties.getQueueCustomerCreated(), false);
    }

    /*
     * Retorna uma instancia do topico ( exchange )
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(properties.getExchangeCustomerCreated());
    }

    /*
     * Cria a ligação entre o topico e a fila quando o assunto do do topico contiver
     * codebrothers.customer. OBS: Este item é desnecessário ao enviar mensagens,
     * deixando ligado apenas para fins ilustrativos No entanto, foi incluido a
     * titulo de modelo. Esse bind deve ser feito por quem deseja "obter" os itens
     * de uma exchange, sendo o responsável por indicar a Exchange para enviar o
     * registro para a fila especificada no bind. Partindo do modelo AMQP: Cada
     * Consumer tem sua propria fila O Consumer assina uma exchange O Consumer
     * efetua o bind para sua fila A Exchange envia uma copia da mensagem a fila
     * especificada
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getExchangeSubjectCustomerBase() + ".#");
    }
    /*
     * @Bean SimpleMessageListenerContainer container(ConnectionFactory
     * connectionFactory, MessageListenerAdapter listenerAdapter) {
     * SimpleMessageListenerContainer container = new
     * SimpleMessageListenerContainer();
     * container.setConnectionFactory(connectionFactory);
     * container.setQueueNames(queueName);
     * container.setMessageListener(listenerAdapter); return container; }
     */

    /* 
      * Docket: Fornece a configuração principa/básical da configuração do Swagger 2 
     */
    @Bean
    public Docket customerApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.codebrothers.services.customer")).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
