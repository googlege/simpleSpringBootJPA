
package de.homedev.jms.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import de.homedev.jms.sender.config.ActiveMQConfig;
import de.homedev.jms.sender.config.SenderConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "de.homedev.jms.sender" })
@Import(value = { ActiveMQConfig.class, SenderConfig.class })
public class SpringJmsSenderApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringJmsSenderApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringJmsSenderApplication.class, args);
		SenderV2 sender = ctx.getBean(SenderV2.class);
		String msg = "Hello Spring JMS ActiveMQ!";
		try {
			sender.send(msg);
			LOGGER.info("Jast send:" + msg);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}
}
