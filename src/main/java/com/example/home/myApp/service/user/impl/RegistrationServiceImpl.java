package com.example.home.myApp.service.user.impl;

import com.example.home.myApp.domain.user.Role;
import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.service.user.interfaces.RegistrationService;
import com.example.home.myApp.service.user.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override public boolean addUser(String name, String username, String password) {
        if(name.isEmpty() ||  password.isEmpty()) {
            return false;
        }
        UserDetails userFromDb = userServiceImpl.loadUserByUsername(name);
        UserDetails userNameFromDb = userServiceImpl.loadUserByUsername(username);
        if (userFromDb != null || userNameFromDb != null) {
            return false;
        }
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userServiceImpl.save(user);
        return true;
    }
}
