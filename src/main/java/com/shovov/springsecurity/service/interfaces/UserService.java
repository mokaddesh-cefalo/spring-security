package com.shovov.springsecurity.service.interfaces;

import com.shovov.springsecurity.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUserName(String userName);
    User save(User user);
}
