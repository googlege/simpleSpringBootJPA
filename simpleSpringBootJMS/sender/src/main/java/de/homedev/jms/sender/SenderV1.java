package de.homedev.jms.sender;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SenderV1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SenderV1.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${app.jms.mqname}")
	private String activemqName;

	public void send(String message) {
		LOGGER.info("sending message='{}'", message);
		ActiveMQQueue queue = new ActiveMQQueue(activemqName);
		jmsTemplate.convertAndSend(queue, message);
	}
}