
package de.homedev.springboot.jpa.test;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import de.homedev.springboot.jpa.config.MyClass;

@Configuration
@Profile("test")
public class TestConfig {

	// @Primary
	@Bean(name = "getMyClass")
	public MyClass getMyClass() throws IOException {
		return new MyClass("created in TestConfig");
	}
}