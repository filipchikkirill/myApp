package com.example.home.myApp.repository.user;

import com.example.home.myApp.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String email);
    User findByName(String name);
}