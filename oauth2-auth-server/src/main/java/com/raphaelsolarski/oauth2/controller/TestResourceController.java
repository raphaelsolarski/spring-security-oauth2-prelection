package com.raphaelsolarski.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.raphaelsolarski.oauth2.service.UserService;

@RestController
@RequestMapping(path = "/test")
public class TestResourceController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String test() {
        return "test";
    }
}
