package com.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springsecurity.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	// WebSecurityConfigurerAdapter implements WebSecurityConfigurer
	// where WebSecurityConfigurer allows customization to the WebSecurity.
	// In most instances users will use EnableWebSecurity annotation and a create
	// Configuration
	// that extends WebSecurityConfigurerAdapter which will automatically be applied
	// to the WebSecurity
	// by the EnableWebSecurity annotation.
	// The main purpose of DaoAthenticationPrvider is that we are using it in
	//
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// auth.authenticationProvider(authenticationProvider());
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// This AuthenticationManagerBuilder can have multiple datasource configurations
		// such as
		// -inMemoryAuthentication
		// -jdbcAuthentication
		// -customized configuration with authenticationProvider
		// We are using here authenticationProvider where adding our
		// DaoAuthenticationProvider to list of providers:

		   auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/managers").hasRole("MANAGER")
			.antMatchers("/leaders/**").hasRole("LEADER")
			.antMatchers("/systems/**").hasRole("ADMIN")
//			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticateTheUser")
			.successHandler(customAuthenticationSuccessHandler)
			.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}

	// beans
	// bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); // set the custom user details service
//		This line sets the PasswordEncoder instance to be used to encode and validate passwords.
//		If not set, the password will be compared as plain text
		auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
		return auth;
	}

}
