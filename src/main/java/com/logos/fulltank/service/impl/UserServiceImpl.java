package com.logos.fulltank.service.impl;

import com.logos.fulltank.dao.UserDao;
import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.IncorrectCredsExceptions;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;
import com.logos.fulltank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j(topic = "User Service")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistException {
        if (checkIfExist(user.getEmail())) {
            log.info("Created new user" + user);
            return userDao.save(user);
        } else {
            log.error("User with email " + user.getEmail() + " is already exist");
            throw new UserAlreadyExistException("User with email " + user.getEmail() + " is already exist!");
        }
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        Optional<User> byId = userDao.findById(id);
        if (byId.isPresent()) {
            log.info("Information about user with id " + id + " ready for you");
            return byId.get();
        } else {
            log.error("User with id " + id + " is not exist");
            throw new UserNotFoundException("User with id " + id + " is not exist");
        }
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> userByEmail = userDao.findUserByEmail(email);
        if (userByEmail.isPresent()) {
            return userByEmail.get();
        } else throw new UserNotFoundException("User with email" + email + " is not exist");
    }

    @Override
    public boolean checkIfExist(String email) {
        Optional<User> byId = userDao.findUserByEmail(email);
        return byId.isEmpty();
    }

    @Override
    public User login(String email, String password) throws IncorrectCredsExceptions {
        Optional<User> byEmail = userDao.findUserByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (user.getPassword().equals(password)) {
                log.info("User " + user.getEmail() + " entered");
                return user;
            }
        }

        throw new IncorrectCredsExceptions();
    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        Optional<User> byId = userDao.findById(id);
        if (byId.isPresent()) {
            userDao.deleteById(id);
            log.info("User with id " + id + " was deleted");
        } else {
            log.error("User with id " + id + " is not exist");
            throw new UserNotFoundException("User with id " + id + " is not exist");
        }
    }
}
