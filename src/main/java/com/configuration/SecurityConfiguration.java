package com.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		UserDetails kurukutUser=User.builder()
				.username("kurukut")
				.password("password")
				.roles("STUDENT")
				.build();
		/*
		 * instead of inbuild uname pwd we can use these credentials to login
		 */
		
		return new InMemoryUserDetailsManager(kurukutUser);
	}
}
