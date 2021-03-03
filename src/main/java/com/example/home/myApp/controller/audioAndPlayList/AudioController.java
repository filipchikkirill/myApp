package com.example.home.myApp.controller.audioAndPlayList;

import com.example.home.myApp.domain.User;
import com.example.home.myApp.domain.audioAndPlayList.Audio;
import com.example.home.myApp.service.audioAndPlayList.interfaces.AudioService;
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
    private AudioService audioServiceImpl;
    @Autowired
    private UserService userService;

    @GetMapping("/audio")
    public String audio(Principal principal, Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);

        Iterable<Audio> audios = audioServiceImpl.getUserAudios(user);
        model.addAttribute("audios", audios);

        return "audio";
    }

    @PostMapping("/audio")
    public String addAudio(Principal principal,
                           String name,
                           String author,
                           MultipartFile file) throws IOException {
        User user = (User) userService.loadUserByUsername(principal.getName());


        audioServiceImpl.addAudio(name, author, user, file);

        return "redirect:/audio";
    }

}
