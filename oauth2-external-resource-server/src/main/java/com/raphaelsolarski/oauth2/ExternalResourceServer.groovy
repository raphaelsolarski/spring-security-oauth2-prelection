package com.raphaelsolarski.oauth2

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@SpringBootApplication
@EnableOAuth2Sso
@Controller
public class ExternalResourceServer {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    public static void main(String[] args) {
        SpringApplication.run(ExternalResourceServer.class, args)
    }
}
