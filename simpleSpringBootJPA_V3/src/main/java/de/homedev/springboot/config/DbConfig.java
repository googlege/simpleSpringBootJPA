
package de.homedev.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.homedev.springboot.jpa.service.UserServiceImpl;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Configuration
@ComponentScan(basePackages = { "de.homedev.springboot.jpa" })
@EntityScan(value = { "de.homedev.springboot.jpa.entity" })
@EnableJpaRepositories(basePackages = { "de.homedev.springboot.jpa.dao" })
@EnableTransactionManagement
// @Profile({ "!selenium", "!test" })
// @Conditional(value = { AndProfilesCondition.class })
public class DbConfig {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

}
