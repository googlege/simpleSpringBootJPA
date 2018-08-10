
package de.homedev.springboot.jpa.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import de.homedev.springboot.jpa.service.UserServiceImpl;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@TestConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "de.homedev.springboot.jpa" })
@EntityScan(value = { "de.homedev.springboot.jpa.entity", "de.homedev.springboot.jpa.common.entities",
        "de.homedev.springboot.jpa.usermanagement.entities" })
@EnableJpaRepositories(basePackages = { "de.homedev.springboot.jpa.dao", "de.homedev.springboot.jpa.common.database" })
@EnableTransactionManagement
public class DbTestConfig {
    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
    private static final String DB_NAME = "postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "password";
    private static final int DB_PORT = 6433;

    public DbTestConfig(@Autowired DataSourceProperties properties) throws IOException {
        System.err.println("ggggg=" + properties.getUsername());
        LOGGER.info("Starting embedded postgres");
        EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.builder().setPort(DB_PORT).setCleanDataDirectory(true)
                .start();
        if (embeddedPostgres != null) {
            LOGGER.info("Embedded postgre SQL started");
        } else {
            LOGGER.info("Embedded postgre SQL not started");
        }

        LOGGER.info("creating database " + DB_NAME);

        try (Connection conn = embeddedPostgres.getPostgresDatabase().getConnection()) {
            Statement statement = conn.createStatement();
            if (!hasDatabase(DB_NAME, statement)) {
                statement.execute("CREATE DATABASE " + DB_NAME);
            }
            if (!hasUser("postgres", statement)) {
                statement.execute("CREATE USER " + USER_NAME + " WITH ENCRYPTED PASSWORD '" + PASSWORD + "'");
                statement.execute("GRANT ALL PRIVILEGES ON DATABASE \"" + DB_NAME + "\" to " + USER_NAME);
            }

            LOGGER.info("database " + DB_NAME + " created");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean hasDatabase(String database, Statement statement) throws SQLException {
        ResultSet r = statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname ='" + database + "\'");
        long result = 0;
        if (r.next()) {
            result = r.getLong(1);
        }
        return (result == 1l);
    }

    private boolean hasUser(String user, Statement statement) throws SQLException {
        ResultSet r = statement.executeQuery("SELECT count(*) FROM pg_user WHERE usename ='" + user + "\'");
        long result = 0;
        if (r.next()) {
            result = r.getLong(1);
        }
        return (result == 1l);
    }

}
