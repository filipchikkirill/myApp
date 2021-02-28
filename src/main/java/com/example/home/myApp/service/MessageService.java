package com.example.home.myApp.service;

import com.example.home.myApp.domain.Message;
import com.example.home.myApp.domain.User;
import com.example.home.myApp.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private FileSaveService fileSaveService;

    public Iterable<Message> getAll() {
        return messageRepo.findAll();
    }

    public Iterable<Message> getMessageByAuthor(User author) {
        return messageRepo.findByAuthor(author);
    }

    public void addMessage(
            User user,
            Message message,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);
        fileSaveService.saveFile(message, file);
        messageRepo.save(message);
    }
}
