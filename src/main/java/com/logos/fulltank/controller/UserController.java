package com.logos.fulltank.controller;

import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;
import com.logos.fulltank.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String enterToProgram() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(Model model,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String email,
                             @RequestParam int age,
                             @RequestParam String password) throws UserNotFoundException, UserAlreadyExistException, SQLException {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAge(age);
        User saved = userService.createUser(user);
        model.addAttribute("user", saved);
        return "userPage";
    }

//    @PostMapping("/login")
//    public String signIn()

}
