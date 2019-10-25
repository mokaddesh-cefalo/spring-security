package com.shovov.springsecurity.controllers;

import com.shovov.springsecurity.model.User;
import com.shovov.springsecurity.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class PublicUrlController {

    @RequestMapping("/")
    public String getGreeting(Principal principal){
        System.out.println(principal);
        if(principal != null) System.out.println(principal.getName());
        return "Hello";
    }

    @RequestMapping("/ooh")
    public String greetUser(HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getSession().getId());
        return "user";
    }

    @RequestMapping("/hoo")
    public String greetAdmin(Principal principal){
        System.out.println(principal);
        return "admin";
    }

    @Autowired
    UserService userRepository;

    @PostMapping("/userPost")
    public User postUser(@RequestBody User user){
        return userRepository.save(user);
    }


}
