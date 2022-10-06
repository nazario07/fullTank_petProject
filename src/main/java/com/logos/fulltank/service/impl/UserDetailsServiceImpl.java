package com.logos.fulltank.service.impl;

import com.logos.fulltank.dao.UserDao;
import com.logos.fulltank.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByEmail = userDao.findUserByEmail(username);
        if (userByEmail.isPresent()) {
            return userByEmail.get();
        } else
            throw new UsernameNotFoundException("User with email" + username + " not found");

    }
}
