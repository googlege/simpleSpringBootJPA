
package de.homedev.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import de.homedev.jms.receiver.config.ActiveMQConfig;
import de.homedev.jms.receiver.config.ReceiverConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "de.homedev.jms.receiver" })
@Import(value = { ActiveMQConfig.class, ReceiverConfig.class })
public class SpringJmsReceiverManuelApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringJmsReceiverManuelApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJmsReceiverManuelApplication.class, args);
        ReceiverV2 receiver = ctx.getBean(ReceiverV2.class);
        try {
            receiver.receive();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        System.exit(0);
    }

}
