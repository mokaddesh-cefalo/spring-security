package com.shovov.springsecurity.controllers;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.User;
import com.shovov.springsecurity.service.interfaces.LanguageService;
import com.shovov.springsecurity.service.interfaces.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;
    @Autowired LanguageService languageService;

    @PostMapping
    public User postUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public User postUser(@RequestParam String userName){
        return userService.findUserByUserName(userName).get();
    }

    @GetMapping("/{userName}/languages")
    public List<Language> getUserLanguageListByUserName(@PathVariable String userName) {
        return languageService.findLanguagesByUserName(userName);
    }

}
