package de.homedev.jms.receiver;

import javax.jms.*;

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

    private int MESSAGE_TIMEOUT_MS = 30 * 1000;

    @Value("${app.jms.mqname}")
    private String activemqName;

    public void receive() throws JMSException {
        int messagesNumber = 0;
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue destination = session.createQueue(activemqName);
        MessageConsumer consumer = session.createConsumer(destination);
        // !!Without receive(MESSAGE_TIMEOUT_MS) MessageConsumer after receiveNoWait() get usually null if message is present!!
        Message message = consumer.receive(MESSAGE_TIMEOUT_MS);// consumer.receiveNoWait();
        while (message != null) {
            messagesNumber++;
            String msgBody = ((TextMessage) message).getText();
            LOGGER.info("received message='{}'", msgBody);
            message = consumer.receiveNoWait();
        }
        consumer.close();
        session.close();
        connection.close();
        LOGGER.info("" + messagesNumber + " messages was in MQ " + activemqName);
    }
}
