package com.raphaelsolarski.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableOAuth2Sso
@Controller
public class ExternalResourceServer extends WebSecurityConfigurerAdapter{

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/secured")
    public String secured() {
        return "secured";
    }

    public static void main(String[] args) {
        SpringApplication.run(ExternalResourceServer.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").permitAll()
                .anyRequest().authenticated();
    }
}
