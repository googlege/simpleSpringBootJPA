
package de.homedev.jms.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import de.homedev.jms.sender.config.CommonSenderAndReceiverConfig;
import de.homedev.jms.sender.config.SenderConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "de.homedev.jms.sender" })
@Import(value = { CommonSenderAndReceiverConfig.class, SenderConfig.class })
public class SpringJmsSenderApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringJmsSenderApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJmsSenderApplication.class, args);
        Sender sender = ctx.getBean(Sender.class);
        String msg = "Hello Spring JMS ActiveMQ!";
        sender.send(msg);
        LOGGER.info("Jast send:" + msg);
    }
}
