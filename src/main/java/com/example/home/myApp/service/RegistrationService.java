package com.example.home.myApp.service;

import com.example.home.myApp.domain.Role;
import com.example.home.myApp.domain.User;
import com.example.home.myApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegistrationService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(String name, String username, String password) {
        if(name.isEmpty() ||  password.isEmpty()) {
            return false;
        }
        UserDetails userFromDb = userService.loadUserByUsername(name);
        UserDetails userNameFromDb = userService.loadUserByUsername(username);
        if (userFromDb != null || userNameFromDb != null) {
            return false;
        }
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userService.save(user);
        return true;
    }
}
