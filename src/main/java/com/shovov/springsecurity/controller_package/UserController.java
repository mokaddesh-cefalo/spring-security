package com.shovov.springsecurity.controller_package;

import com.shovov.springsecurity.model.User;
import com.shovov.springsecurity.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public User postUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public User postUser(@RequestParam String userName){
        return userService.findUserByUserName(userName).get();
    }

}
