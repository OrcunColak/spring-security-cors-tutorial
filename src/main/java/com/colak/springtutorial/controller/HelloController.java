package com.colak.springtutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is used by unit tests
@RestController
public class HelloController {

    // http://localhost:8080/hello
    @GetMapping(path = "/hello")
    String sayHello() {
        return "Hello";
    }
}
