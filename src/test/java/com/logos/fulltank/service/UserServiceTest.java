package com.logos.fulltank.service;

import com.logos.fulltank.dao.UserDao;
import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    UserDao userDao;

    @Test
    void createUser() throws UserNotFoundException, UserAlreadyExistException, SQLException {
        User user = new User("John", "Walker", "walker@gmail.com", "john40", 100);
        Mockito.when(userDao.save(user)).thenReturn(user);
        User save = userService.createUser(user);
        Assertions.assertEquals(save, user);
    }

    @Test
    void getUserById() {
        User user = new User("John", "Walker", "walker@gmail.com", "john40", 100);
        Mockito.when(userDao.findById(user.getId())).thenReturn(Optional.of(user));
        User userById;
        try {
            userById = userService.getUserById(user.getId());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(user, Optional.of(userById).get());
    }

    @Test
    void getUserByEmail() {
        User user = new User("John", "Walker", "walker@gmail.com", "john40", 100);
        Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        User userByEmail;
        try {
            userByEmail = userService.getUserByEmail(user.getEmail());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(user, Optional.of(userByEmail).get());
    }


    @Test
    void checkIfExist() {
        User user = new User("John", "Walker", "walker@gmail.com", "john40", 100);
        Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        boolean actual;
        try {
             actual = userService.checkIfExist(user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(Optional.of(user).isEmpty(),actual);

    }

    @Test
    void login() {
        User user = new User("John", "Walker", "walker@gmail.com", "john40", 100);
        Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

    }

    @Test
    void deleteUser() {
    }
}