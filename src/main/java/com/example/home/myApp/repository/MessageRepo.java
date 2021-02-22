package com.example.home.myApp.repository;

import com.example.home.myApp.domain.Message;
import com.example.home.myApp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByAuthor(User author);
}
