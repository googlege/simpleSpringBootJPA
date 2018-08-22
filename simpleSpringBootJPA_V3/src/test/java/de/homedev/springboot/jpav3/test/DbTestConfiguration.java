
package de.homedev.springboot.jpav3.test;

import java.io.IOException;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import de.homedev.springboot.jpav3.config.DataSourceContainer;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@TestConfiguration
public class DbTestConfiguration {

	@Primary
	@Bean(name = "dataSourceContainer")
	public DataSourceContainer getDataSourceContainer() throws IOException {
		int port = 6433;// Integer.valueOf(environment.getRequiredProperty("embeddeddb.datasource.port"));
		EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.builder().setPort(port).setCleanDataDirectory(true).start();
		return new DataSourceContainer(embeddedPostgres.getPostgresDatabase());
	}

}
