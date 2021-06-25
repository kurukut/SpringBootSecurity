package com.configuration;


import static com.configuration.ApplicationUserRole.ADMIN;
import static com.configuration.ApplicationUserRole.STUDENT;//ctrl shift M

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder passwordEncoder;
	/*
	 * we are using constructor injection coz 
	 * autowired on a final field does not work
	 * 
	 */
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
	@Autowired
	public SecurityConfiguration(PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/","index","/css/*","/js/*")
		.permitAll()
		.anyRequest()
		.authenticated()//uname and pwd
		.and()
		.httpBasic();/*
		an alert will pop up to enter uname and password
		drawback
			we cannot logout
			coz uname and passname is send on every single req
		*/
		
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		UserDetails kurukutUser=User.builder()
				.username("kuru")
				.password(passwordEncoder.encode("password"))
				.roles(STUDENT.name())
				.build();
		/*
		 * instead of inbuild uname pwd we can use these credentials to login
		 */
		
		UserDetails murumonUser=User.builder()
				.username("muru")
				.password(passwordEncoder.encode("password"))
				.roles(ADMIN.name())
				.build();
		
		return new InMemoryUserDetailsManager(kurukutUser,
				murumonUser);
	}
}
