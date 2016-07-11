package com.raphaelsolarski.oauth2

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@RequestMapping(path = "/")
public class ExternalResourceServer {

    @RequestMapping
    String home() {
        return "Hello World!"
    }

    public static void main(String[] args) {
        SpringApplication.run(ExternalResourceServer.class, args);
    }

}
