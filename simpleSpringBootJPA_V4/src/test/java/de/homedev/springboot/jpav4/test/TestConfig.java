
package de.homedev.springboot.jpav4.test;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import de.homedev.springboot.jpav4.config.MyClass;

@Configuration
@Profile("test")
public class TestConfig {

    @Primary
    @Bean(name = "getMyClass")
    public MyClass getMyClass() throws IOException {
        return new MyClass("created in TestConfig");
    }
}
