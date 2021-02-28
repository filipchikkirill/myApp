package com.example.home.myApp.controller;

import com.example.home.myApp.domain.Message;
import com.example.home.myApp.domain.User;
import com.example.home.myApp.service.MessageService;
import com.example.home.myApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

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



    @GetMapping("/myMessages/{user}")
    public String userMessges(
            Principal principal,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ) {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        Iterable<Message> messages = user.getMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "myMessages";
    }

    @PostMapping("/myMessages/{user}")
    public String updateMessage(
            Principal principal,
            @PathVariable Long user,
            @RequestParam(name="id",required = false) Message message,
            @RequestParam("text") String text,
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        if (message == null) {
            message = new Message();
            message.setAuthor(currentUser);
        }
        if (currentUser.equals(message.getAuthor())) {
            if (!ObjectUtils.isEmpty(text)) {
                message.setText(text);
            }

            if (!ObjectUtils.isEmpty(title)) {
                message.setTitle(title);
            }

            messageService.addMessage(currentUser,message,file);
        }

        return "redirect:/myMessages/" + user;
    }
}
