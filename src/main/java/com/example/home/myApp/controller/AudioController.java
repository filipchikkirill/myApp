package com.example.home.myApp.controller;

import com.example.home.myApp.domain.User;
import com.example.home.myApp.domain.audio.Audio;
import com.example.home.myApp.repository.AudioRepo;
import com.example.home.myApp.service.AudioService;
import com.example.home.myApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class AudioController {
    @Autowired
    private AudioService audioService;
    @Autowired
    private UserService userService;

    @GetMapping("/audio")
    public String audio(Principal principal, Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);

        Iterable<Audio> audios = audioService.getUserAudios(user);
        model.addAttribute("audios", audios);

        return "audio";
    }

    @PostMapping("/audio")
    public String addAudio(Principal principal,
                           String name,
                           String author,
                           MultipartFile file) throws IOException {
        User user = (User) userService.loadUserByUsername(principal.getName());


        audioService.addAudio(name, author, user, file);

        return "redirect:/audio";
    }

}
