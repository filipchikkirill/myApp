package com.example.home.myApp.service.user.impl;

import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.repository.user.UserRepo;
import com.example.home.myApp.service.user.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User userFindByUsername = userRepo.findByUsername(username);
        User userFindByName = userRepo.findByName(username);

        if(userFindByUsername != null)
        {
            return userFindByUsername;
        }

        if(userFindByName != null)
        {
            return userFindByName;
        }

        return null;
    }

    @Override public void save(User user) {
        userRepo.save(user);
    }
}
