package com.example.home.myApp.service.message.interfaces;

import com.example.home.myApp.domain.message.Message;
import com.example.home.myApp.domain.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MessageService {
    Iterable<Message> getAll();

    Iterable<Message> getMessageByAuthor(User author);

    void addMessage(
            User user,
            Message message,
            @RequestParam("file") MultipartFile file
    ) throws IOException;
}
