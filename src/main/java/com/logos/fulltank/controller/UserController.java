package com.logos.fulltank.controller;

import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.IncorrectCredsExceptions;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;
import com.logos.fulltank.service.FuellingStationService;
import com.logos.fulltank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@Controller
@RequestMapping
@Slf4j(topic = "User Controller")
public class UserController {

    private final UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(password));
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

    @PreAuthorize("hasAnyAuthority({'ADMIN','USER'})")
    @GetMapping("/workPage")
    public String fuelShop(Model model, Authentication authentication) throws UserNotFoundException {
        User user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        return "workPage";
    }

    @PreAuthorize("hasAnyAuthority({'ADMIN','USER'})")
    @GetMapping("/cabinet")
    public String cabinet(Model model, Authentication authentication) throws UserNotFoundException {
        User user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        return "cabinet";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
