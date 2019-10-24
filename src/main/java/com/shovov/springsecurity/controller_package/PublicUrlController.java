package com.shovov.springsecurity.controller_package;

import com.shovov.springsecurity.model.User;
import com.shovov.springsecurity.model.interfaces.UserRepository;
import com.shovov.springsecurity.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public String greetUser(Principal principal){
        System.out.println(principal);
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
