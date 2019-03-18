
package de.homedev.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import de.homedev.jms.receiver.config.CommonSenderAndReceiverConfig;
import de.homedev.jms.receiver.config.ReceiverConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "de.homedev.jms.receiver" })
@Import(value = { CommonSenderAndReceiverConfig.class, ReceiverConfig.class })
public class SpringJmsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringJmsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsApplication.class, args);
        LOGGER.info("waiting for jms messages");
    }

}
