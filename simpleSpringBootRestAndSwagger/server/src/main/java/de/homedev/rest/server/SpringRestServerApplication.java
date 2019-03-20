
package de.homedev.rest.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import de.homedev.rest.server.config.RestServerConfiguration;
import de.homedev.rest.server.config.SwaggerConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "de.homedev.rest.server" })
@Import(value = { RestServerConfiguration.class, SwaggerConfig.class })
public class SpringRestServerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringRestServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringRestServerApplication.class, args);
//        SenderV2 sender = ctx.getBean(SenderV2.class);
//        String msg = "Hello Spring JMS ActiveMQ! i=";
//        try {
//            for (int i = 0; i < 20; i++) {
//                sender.send(msg + i);
//            }
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//        System.exit(0);
    }
}
