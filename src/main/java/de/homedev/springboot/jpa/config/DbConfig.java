
package de.homedev.springboot.jpa.config;

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
@ComponentScan(basePackages = { "de.homedev.springboot.jpa" })
@EntityScan(value = { "de.homedev.springboot.jpa.entity", "de.homedev.springboot.jpa.common.entities",
        "de.homedev.springboot.jpa.usermanagement.entities" })
@EnableJpaRepositories(basePackages = { "de.homedev.springboot.jpa.dao", "de.homedev.springboot.jpa.common.database" })
@EnableTransactionManagement
public class DbConfig {

}
