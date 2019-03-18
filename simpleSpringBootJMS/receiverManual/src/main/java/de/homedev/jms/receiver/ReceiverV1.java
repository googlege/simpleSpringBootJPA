package de.homedev.jms.receiver;

import javax.jms.Message;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReceiverV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverV1.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${app.activemq.name}")
    private String activemqName;

    public void receive() {
        int messagesNumber = 0;
        Message message = null;
        ActiveMQQueue queue = new ActiveMQQueue(activemqName);
        while ((message = jmsTemplate.receive(queue)) != null) {
            messagesNumber++;
        }
        LOGGER.info("" + messagesNumber + " messages was in MQ " + activemqName);
    }
}
