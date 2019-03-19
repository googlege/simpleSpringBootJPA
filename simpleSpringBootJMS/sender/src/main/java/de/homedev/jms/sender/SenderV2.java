package de.homedev.jms.sender;

import javax.jms.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SenderV2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderV2.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("${app.jms.mqname}")
    private String activemqName;

    public void send(String messageStr) throws JMSException {
        LOGGER.info("sending message='{}'", messageStr);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue destination = session.createQueue(activemqName);
        MessageProducer producer = session.createProducer(destination);
        producer.setPriority(9);
        TextMessage message = session.createTextMessage(messageStr);
        producer.send(destination, message);
        producer.close();
        session.close();
        connection.close();
    }
}