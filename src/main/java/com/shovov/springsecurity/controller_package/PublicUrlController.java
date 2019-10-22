package com.shovov.springsecurity.controller_package;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicUrlController {

    @RequestMapping("/")
    public String getGreeting(){
        return "Hello";
    }
}
