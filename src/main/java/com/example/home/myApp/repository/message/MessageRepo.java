package com.example.home.myApp.repository.message;

import com.example.home.myApp.domain.message.Message;
import com.example.home.myApp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByAuthor(User author);
}
