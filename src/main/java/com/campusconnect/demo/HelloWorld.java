package com.campusconnect.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    @GetMapping("/hello")
    public String hello() {
        return "hello World";
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        logger.info("hello " + name);
        return "hello " + name;
    }

    @GetMapping("/greet")
    public String greetUser(@RequestParam(defaultValue = "Guest") String username) {
        return "Good Morning "+ username;

    }
}
