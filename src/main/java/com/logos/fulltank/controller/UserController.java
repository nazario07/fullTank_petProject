package com.logos.fulltank.controller;

import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.IncorrectCredsExceptions;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;
import com.logos.fulltank.service.FuellingStationService;
import com.logos.fulltank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;



@Controller
@RequestMapping
@Slf4j(topic = "User Controller")
public class UserController {

    private final UserService userService;

    private final FuellingStationService fuellingStationService;


    public UserController(UserService userService, FuellingStationService fuellingStationService) {
        this.userService = userService;
        this.fuellingStationService = fuellingStationService;
    }


    @GetMapping
    public String enterToProgram() {
        return "mainPage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String toRegister() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(Model model,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String email,
                             @RequestParam int age,
                             @RequestParam String password) throws UserNotFoundException, UserAlreadyExistException,
            SQLException {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAge(age);
        User saved = userService.createUser(user);
        model.addAttribute("user", saved);
        return "workPage";
    }

    @PostMapping("/login")
    public String signIn(Model model,
                         @RequestParam String email,
                         @RequestParam String password) {
        User user = null;
        try {
            user = userService.login(email, password);
            model.addAttribute("user", user);
            return "workPage";
        } catch (IncorrectCredsExceptions e) {
            log.error("Incorrect email or password");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/workPage/{id}")
    public String fuelShop(Model model, @PathVariable int id) throws UserNotFoundException {
        User userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        return "workPage";
    }

    @GetMapping("/cabinet/{id}")
    public String cabinet(Model model, @PathVariable int id) throws UserNotFoundException {
        User userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        return "cabinet";
    }


}
