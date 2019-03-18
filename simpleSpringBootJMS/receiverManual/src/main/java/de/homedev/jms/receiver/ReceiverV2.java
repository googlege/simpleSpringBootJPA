package de.homedev.jms.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReceiverV2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverV2.class);

	@Autowired
	private ConnectionFactory connectionFactory;

	@Value("${app.jms.mqname}")
	private String activemqName;

	public void receive() throws JMSException {
		int messagesNumber = 0;
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue destination = session.createQueue(activemqName);
		MessageConsumer consumer = session.createConsumer(destination);
		Message message = null;
		while ((message = consumer.receiveNoWait()) != null) {
			messagesNumber++;
		}
		connection.close();
		LOGGER.info("" + messagesNumber + " messages was in MQ " + activemqName);
	}
}
