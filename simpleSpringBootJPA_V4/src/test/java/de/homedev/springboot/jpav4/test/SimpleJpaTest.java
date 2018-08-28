package de.homedev.springboot.jpav4.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import de.homedev.springboot.jpav4.service.IUserService;

@ActiveProfiles({ "test" }) // ,"selenium"
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "de.homedev.springboot.jpav4" })
@PropertySource(value = "classpath:application-test.properties", ignoreResourceNotFound = false)
@Import({ TestConfig.class })
public class SimpleJpaTest {
    private static final Logger log = LoggerFactory.getLogger(SimpleJpaTest.class);

    @Autowired
    private ConfigurableApplicationContext ctx;
    private IUserService simpleFassade;

    @Before
    public void setUp() {
        simpleFassade = (IUserService) ctx.getBean(IUserService.SERVICE_NAME);
    }

    @Test
    public void beanInitialisationTest() {
        Assert.assertNotNull(simpleFassade);
    }

    @Test
    public void printInDbInitMessage() {
        simpleFassade.printMessage();

    }
}
