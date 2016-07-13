package com.raphaelsolarski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
@RestController
public class AuthServerApplication extends WebSecurityConfigurerAdapter {

	@RequestMapping({ "/user", "/me" })
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and().exceptionHandling().authenticationEntryPoint(new BasicAuthenticationEntryPoint());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}


	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}
}
