package de.homedev.jms.sender.config;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {

	// @Bean
	// public CachingConnectionFactory cachingConnectionFactory(ConnectionFactory
	// connectionFactory) {
	// return new CachingConnectionFactory(connectionFactory);
	// }

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate result = new JmsTemplate(connectionFactory);
		result.setSessionTransacted(false);
		result.setDeliveryDelay(-1);
		return result;
	}

	@Bean
	@ConditionalOnClass(JmsMessagingTemplate.class)
	@ConditionalOnMissingBean(JmsMessagingTemplate.class)
	public JmsMessagingTemplate jmsMessagingTemplate(ConnectionFactory connectionFactory) {
		JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate(connectionFactory);
		jmsMessagingTemplate.getJmsTemplate().setSessionTransacted(false);
		return jmsMessagingTemplate;
	}

}
