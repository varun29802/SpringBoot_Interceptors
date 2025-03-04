package com.spring.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/api/test")
    public String welcomeInterceptor(){
        return "Hello Welcome To Interceptor Concept";
    }

    @GetMapping("/api/public")
    public String publicEndpoint() {
        return "This is a public endpoint. No authentication needed.";
    }

    @GetMapping("/api/test/{name}")
    public String welcomeUser(@PathVariable String name){
        return "Welcome "+name+" You are a authenticated user, Thank you.";
    }

}
