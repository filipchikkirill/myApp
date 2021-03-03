package com.example.home.myApp.service.message.impl;

import com.example.home.myApp.domain.message.Message;
import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.repository.message.MessageRepo;
import com.example.home.myApp.utils.FileSaveService;
import com.example.home.myApp.service.message.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private FileSaveService fileSaveService;

    @Override public Iterable<Message> getAll() {
        return messageRepo.findAll();
    }

    @Override public Iterable<Message> getMessageByAuthor(User author) {
        return messageRepo.findByAuthor(author);
    }

    @Override public void addMessage(
            User user,
            Message message,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);
        fileSaveService.saveFile(message, file);
        messageRepo.save(message);
    }
}
