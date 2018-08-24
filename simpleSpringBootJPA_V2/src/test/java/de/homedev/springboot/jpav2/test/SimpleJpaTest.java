package de.homedev.springboot.jpav2.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import de.homedev.springboot.jpav2.config.DbConfig;
import de.homedev.springboot.jpav2.entity.UserEntity;
import de.homedev.springboot.jpav2.entity.UserInfoEntity;
import de.homedev.springboot.jpav2.entity.UserRightEntity;
import de.homedev.springboot.jpav2.service.IUserService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import({ DbConfig.class })
@PropertySource(value = "application-test.properties", ignoreResourceNotFound = false)
public class SimpleJpaTest {
	private static final Logger log = LoggerFactory.getLogger(SimpleJpaTest.class);

	@Autowired
	private ConfigurableApplicationContext ctx;

	private IUserService simpleFassade;

	// @Value("${embeddeddb.datasource.port:null}")
	private String portThis;

	@Before
	public void setUp() {
		String port = ctx.getEnvironment().getProperty("embeddeddb.datasource.port");
		System.err.println("port=" + port);
		System.err.println("portThis=" + portThis);
		// 22.08.2018 Spring Boot 2.0.4.RELEASE Output: port=null
		simpleFassade = (IUserService) ctx.getBean(IUserService.SERVICE_NAME);
		Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(ctx.getEnvironment());
		for (ConfigurationPropertySource s : sources) {
			System.err.println(s.toString());
		}
	}

	@Test
	public void fassadeInitialisationTest() {
		Assert.assertNotNull(simpleFassade);
	}

	@Test
	public void testInsertAndFind() {
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
		List<UserEntity> list1 = fassade.save(list);
		Assert.assertNotNull(list1);
		Assert.assertFalse(list1.isEmpty());
		Assert.assertNotNull(list1.get(0).getId());
		Assert.assertEquals(username, list1.get(0).getUsername());
		Assert.assertEquals(password, list1.get(0).getPassword());
		entity = fassade.findByUsernameAndPassword(username, password);
		Assert.assertNotNull(entity);
		Assert.assertEquals(username, entity.getUsername());
		Assert.assertEquals(password, entity.getPassword());
		Assert.assertEquals(list1.get(0).getId(), entity.getId());

		// READING UserInfo and List<UserRightEntity> CAUSE LAZY LOADING
		// EXCEPTION ON THIS PLACE
		// POSIBLE SOLUTIONS
		// 1.Sent JPA Graph as parameter in function
		// findByUsernameAndPassword
		// 2.Reading data before entity "UserEntity" was detached
		// 3.Reading UserInfo and List<UserRightEntity> with other service
		// functions
		// !!First solution is the best!!
	}
}
