
package de.homedev.springboot.jpav3.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Configuration
public class DevConfiguration {

	@Bean(name = "dataSourceContainer")
	public DataSourceContainer getDataSourceContainer() {
		DataSourceProperties prop = new DataSourceProperties();
		prop.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		prop.setUrl("jdbc:hsqldb:mem:test");
		prop.setUsername("sa");
		prop.setPassword("");
		final HikariDataSource result = prop.initializeDataSourceBuilder().type(HikariDataSource.class).build();
		return new DataSourceContainer(result);
	}

}
