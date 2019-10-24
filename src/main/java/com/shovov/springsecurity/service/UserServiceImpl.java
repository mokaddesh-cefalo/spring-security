package com.shovov.springsecurity.service;

import com.shovov.springsecurity.model.User;
import com.shovov.springsecurity.model.interfaces.UserRepository;
import com.shovov.springsecurity.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findUserByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }
}
