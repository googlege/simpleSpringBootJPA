package de.homedev.jms.receiver.config;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.JmsDestinationAccessor;

@Configuration
public class ReceiverConfig {

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate result = new JmsTemplate(connectionFactory);
		result.setReceiveTimeout(JmsDestinationAccessor.RECEIVE_TIMEOUT_NO_WAIT);
		result.setSessionTransacted(false);
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
