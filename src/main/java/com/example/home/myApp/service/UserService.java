package com.example.home.myApp.service;

import com.example.home.myApp.domain.User;
import com.example.home.myApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
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
}
