package de.homedev.jms.sender.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {

//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory(ConnectionFactory connectionFactory) {
//        return new CachingConnectionFactory(connectionFactory);
//    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate result = new JmsTemplate(connectionFactory);
        result.setDeliveryDelay(-1);
        return result;
    }

}
