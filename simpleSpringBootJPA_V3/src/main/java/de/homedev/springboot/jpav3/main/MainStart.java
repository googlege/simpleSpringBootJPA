
package de.homedev.springboot.jpav3.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import de.homedev.springboot.jpav3.config.DbConfig;
import de.homedev.springboot.jpav3.config.DevConfiguration;
import de.homedev.springboot.jpav3.entity.UserEntity;
import de.homedev.springboot.jpav3.entity.UserInfoEntity;
import de.homedev.springboot.jpav3.entity.UserRightEntity;
import de.homedev.springboot.jpav3.service.IUserService;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@SpringBootApplication
@Import({ DevConfiguration.class, DbConfig.class })
public class MainStart {
	private static final Logger log = LoggerFactory.getLogger(MainStart.class);

	// @Bean(name = "dataSourceContainer")
	// public DataSourceContainer getDataSourceContainer() {
	// DataSourceProperties prop = new DataSourceProperties();
	// prop.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
	// prop.setUrl("jdbc:hsqldb:mem:test");
	// prop.setUsername("sa");
	// prop.setPassword("");
	// final HikariDataSource result =
	// prop.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	// return new DataSourceContainer(result);
	// }

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext ctx = SpringApplication.run(MainStart.class, args);
			String username = "musterman";
			String password = "admin";
			IUserService fassade = (IUserService) ctx.getBean(IUserService.SERVICE_NAME);
			UserEntity entity = new UserEntity();
			entity.setPassword(password);
			entity.setUsername(username);
			UserInfoEntity info = new UserInfoEntity();
			info.setName("Musterman");
			info.setVorname("Robert");
			info.setEmail("Robert.Musterman@google.com");
			info.setUserEntity(entity);
			entity.setUserInfo(info);
			List<UserRightEntity> userRightsList = new ArrayList<>(10);
			UserRightEntity right = new UserRightEntity();
			right.setUserRight(1);
			right.setUserEntity(entity);
			userRightsList.add(right);
			right = new UserRightEntity();
			right.setUserRight(2);
			right.setUserEntity(entity);
			userRightsList.add(right);
			entity.setUserRightsList(userRightsList);
			List<UserEntity> list = new ArrayList<>(1);
			list.add(entity);
			fassade.save(list);
			entity = fassade.findByUsernameAndPassword(username, password);

			log.info("entity username:" + entity.getUsername() + "; entity password:" + entity.getPassword());
			// READING UserInfo and List<UserRightEntity> CAUSE LAZY LOADING
			// EXCEPTION ON THIS PLACE
			// POSIBLE SOLUTIONS
			// 1.Sent JPA Graph as parameter in function
			// findByUsernameAndPassword
			// 2.Reading data before entity "UserEntity" was detached
			// 3.Reading UserInfo and List<UserRightEntity> with other service
			// functions
			// !!First solution is the best!!

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
