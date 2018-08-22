package de.homedev.springboot.jpav2.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

@Component
public class DBInitializingBean implements InitializingBean {

	@Autowired
	private Environment environment;

	private EmbeddedPostgres embeddedPostgres;

	@Override
	public void afterPropertiesSet() throws Exception {
		int port = 6433;// Integer.valueOf(environment.getRequiredProperty("embeddeddb.datasource.port"));
		embeddedPostgres = EmbeddedPostgres.builder().setPort(port).setCleanDataDirectory(true).start();
	}

	public EmbeddedPostgres getEmbeddedPostgres() {
		return embeddedPostgres;
	}

	public Environment getEnvironment() {
		return environment;
	}

}