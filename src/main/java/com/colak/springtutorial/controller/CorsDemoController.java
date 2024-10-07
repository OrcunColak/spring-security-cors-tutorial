package com.colak.springtutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// This class is used by browser
@Controller
public class CorsDemoController {

    // http://localhost:8080/
    @GetMapping("/")
    public String home() {
        return "tester.html";
    }

    @PostMapping("/test")
    @ResponseBody

    // This annotation allows any domain to access the endpoint.
    // @CrossOrigin
    public String test() {
        return "Hello World!";
    }
}
