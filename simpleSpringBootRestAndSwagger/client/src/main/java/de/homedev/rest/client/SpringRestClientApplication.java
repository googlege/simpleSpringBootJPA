
package de.homedev.rest.client;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import de.homedev.rest.client.config.RestClientConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "de.homedev.rest.client" })
@Import(value = { RestClientConfig.class })
public class SpringRestClientApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringRestClientApplication.class);

    public static void main(final String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(SpringRestClientApplication.class, args);

        final SenderV1 sender = ctx.getBean(SenderV1.class);
        try (InputStream is = ConfigurableApplicationContext.class.getResourceAsStream("/messageList.txt");) {
            String msg = IOUtils.toString(is, Charset.forName("UTF-8"));
            // receiver.sendV2();
            sender.send(msg);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        System.exit(0);
    }

}
