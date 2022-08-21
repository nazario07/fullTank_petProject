package com.logos.fulltank.service;

import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.IncorrectCredsExceptions;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;

import java.sql.SQLException;

public interface UserService {
    User createUser(User user) throws UserNotFoundException, SQLException, UserAlreadyExistException;
    User getParticipantById(int id) throws UserNotFoundException;
    User getParticipantByEmail(String email) throws UserNotFoundException, SQLException;
    User login(String email, String password) throws IncorrectCredsExceptions;
    void deleteUser(int id) throws UserNotFoundException;

}
