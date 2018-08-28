
package de.homedev.springboot.jpav4.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "!selenium", "!test" })
@Conditional(value = { AndProfilesCondition.class })
public class DevConfig {

	// @Primary
	@Bean(name = "getMyClass")
	public MyClass getMyClass() throws IOException {
		return new MyClass("created in DbConfig");
	}
}