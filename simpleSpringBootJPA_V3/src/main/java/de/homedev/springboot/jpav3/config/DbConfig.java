
package de.homedev.springboot.jpav3.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@ComponentScan(basePackages = { "de.homedev.springboot.jpav3" })
@EntityScan(value = { "de.homedev.springboot.jpav3.entity" })
@EnableJpaRepositories(basePackages = { "de.homedev.springboot.jpav3.dao" })
@EnableTransactionManagement
public class DbConfig {

	@Primary
	@Bean(name = "dataSource")
	public DataSource getDataSource(final DataSourceContainer dataSourceContainer) {
		return dataSourceContainer.getDataSource();
	}

}
