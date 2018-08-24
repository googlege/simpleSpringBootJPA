
package de.homedev.springboot.jpav2.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan(basePackages = { "de.homedev.springboot.jpav2" })
@EntityScan(value = { "de.homedev.springboot.jpav2.entity" })
@EnableJpaRepositories(basePackages = { "de.homedev.springboot.jpav2.dao" })
@EnableTransactionManagement
public class DbConfig {

}
