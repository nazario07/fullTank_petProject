package com.logos.fulltank.service;

import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.IncorrectCredsExceptions;
import com.logos.fulltank.exception.UserNotFoundException;

public interface UserService {
    User createUser(User user);
    User login(String email, String password) throws IncorrectCredsExceptions;
    void deleteUser(int id) throws UserNotFoundException;

}
