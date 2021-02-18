package com.example.home.myApp.repository;

import com.example.home.myApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String email);
    User findByName(String name);
}