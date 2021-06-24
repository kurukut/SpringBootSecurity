package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
	@Bean
	/*
	 *   See, once you provide
	 *  your bean to the spring container, you do not need 
	 *  to declare variable and create an object anywhere. 
	 *  Spring brings in Dependency Injection there. 
	 *  It creates a singleton object and keeps it in the container.
	 *   Now, whenever you need this object, 
	 *   just do @Autowired on object type you need. 
	 *   That instance will be available 
	 * directly from the container. And, you dont need to use new() anywhere.
	 */
	public PasswordEncoder passwordEncoder() {
		//an interface
		return new BCryptPasswordEncoder(10);
	}
}
