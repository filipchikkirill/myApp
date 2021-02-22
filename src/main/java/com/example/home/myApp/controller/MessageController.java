package com.example.home.myApp.controller;

import com.example.home.myApp.domain.Message;
import com.example.home.myApp.domain.User;
import com.example.home.myApp.service.MessageService;
import com.example.home.myApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public String messages(Principal principal,
                           Model model,
                           @RequestParam(required = false, defaultValue = "") String filter) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        Iterable<Message> messages;
        User author = (User) userService.loadUserByUsername(filter);
        if (author != null) {
            messages = messageService.getMessageByAuthor(author);
        } else {
            messages = messageService.getAll();
        }
        model.addAttribute("messages", messages);

        return "messages";
    }

    @PostMapping("/messages")
    public String addMessage(
            Principal principal,
            Message message,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        messageService.addMessage(
                (User) userService.loadUserByUsername(principal.getName()),
                message,
                file
                );

        return "redirect:messages";
    }
}
