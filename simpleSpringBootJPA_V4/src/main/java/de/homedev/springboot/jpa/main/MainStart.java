
package de.homedev.springboot.jpa.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import de.homedev.springboot.jpa.config.DevConfig;
import de.homedev.springboot.jpa.service.IUserService;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@SpringBootApplication
@Import({ DevConfig.class })
@ComponentScan(basePackages = { "de.homedev.springboot.jpa" })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
public class MainStart {
	private static final Logger log = LoggerFactory.getLogger(MainStart.class);

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext ctx = SpringApplication.run(MainStart.class, args);

			IUserService fassade = (IUserService) ctx.getBean(IUserService.SERVICE_NAME);
			fassade.printMessage();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
