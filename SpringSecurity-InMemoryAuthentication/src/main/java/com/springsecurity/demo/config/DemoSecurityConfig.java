package com.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add user for in memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
				.withUser(users.username("Asmaa").password("xyz123").roles("MANAGER","EMPLOYEE"))
				.withUser(users.username("Fatma").password("xyz123").roles("LEADER","EMPLOYEE"))
				.withUser(users.username("Sarah").password("xyz123").roles("ADMIN","EMPLOYEE"))
				.withUser(users.username("Zainab").password("xyz123").roles("EMPLOYEE"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/managers").hasRole("MANAGER")
			.antMatchers("/leaders/**").hasRole("LEADER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.antMatchers("/").hasRole("EMPLOYEE")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
