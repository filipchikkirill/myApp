package com.example.home.myApp.utils;

import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.service.user.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = (User) userServiceImpl.loadUserByUsername(username);

        if(user != null && (user.getUsername().equals(username) || user.getName().equals(username)))
        {
            if(!passwordEncoder.matches(password, user.getPassword()))
            {
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        else
            throw new BadCredentialsException("Username not found");
    }

    public boolean supports(Class<?> arg)
    {
        return true;
    }
}