
package de.homedev.springboot.jpa.test.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(value = { "de.homedev.springboot.jpa.entity" })
@EnableJpaRepositories(basePackages = { "de.homedev.springboot.jpa.dao" })
@EnableTransactionManagement
@Profile("test")
public class DbTestConfig {

}
