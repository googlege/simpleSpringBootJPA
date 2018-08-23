package de.homedev.springboot.jpav2.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

@Component
public class DBInitializingBean { // implements InitializingBean {

    @Autowired
    private Environment environment;

    private EmbeddedPostgres embeddedPostgres;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        int port = 6435;// Integer.valueOf(environment.getRequiredProperty("embeddeddb.datasource.port"));
        EmbeddedPostgres.Builder builder = null;
        try {
            builder = EmbeddedPostgres.builder().setPort(port).setCleanDataDirectory(true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.err.println("builder =" + builder);
        if (embeddedPostgres != null) {
            try {
                embeddedPostgres = builder.start();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        System.err.println("embeddedPostgres =" + embeddedPostgres);
    }

    public EmbeddedPostgres getEmbeddedPostgres() {
        return embeddedPostgres;
    }

    public Environment getEnvironment() {
        return environment;
    }

}