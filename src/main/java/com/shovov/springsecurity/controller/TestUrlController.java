package com.shovov.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUrlController {

    @RequestMapping("/")
    public String index(){
        return "<h2>Hello</h2>";
    }

    @RequestMapping("/common")
    public String common(){
        return "<h2>Common</h2>";
    }

    @RequestMapping("/user")
    public String user() {
        return "<h2>User</h2>";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "<h2>Admin</h2>";
    }
}
