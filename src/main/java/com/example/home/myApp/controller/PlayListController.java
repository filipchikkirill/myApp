package com.example.home.myApp.controller;

import com.example.home.myApp.domain.User;
import com.example.home.myApp.domain.audio.Audio;
import com.example.home.myApp.domain.audio.PlayList;
import com.example.home.myApp.service.AudioService;
import com.example.home.myApp.service.PlayListService;
import com.example.home.myApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PlayListController {
    @Autowired
    private PlayListService playListService;
    @Autowired
    private AudioService audioService;
    @Autowired
    private UserService userService;

    @GetMapping("/playList")
    public String playlist(Principal principal,
                           @RequestParam(name="id", defaultValue="-1", required=false) String id,
                           Model model) {
        if (id.equals("-1")) {
            return "redirect:/playLists";
        }

        PlayList playList = playListService.getById(Long.parseLong(id));
        model.addAttribute("playList", playList);

        User user = (User) userService.loadUserByUsername(principal.getName());
        if (user.equals(playList.getUser())) {
            model.addAttribute("owner", true);
            Iterable<Audio> audios = audioService.getAll();
            model.addAttribute("audios", audios);
        }
        return "playList";
    }

    @PostMapping("/playList")
    public String addAudio(String audio, String listId) {
        Audio theAudio = audioService.getAudioById(Long.parseLong(audio));
        PlayList thePlayList = playListService.getById(Long.parseLong(listId));

        playListService.addAudioToPlayList(
                theAudio,
                thePlayList);

        return "redirect:/playList?id=" + listId;
    }

    @GetMapping("/playLists")
    public String playLists(Model model) {
        Iterable<PlayList> playLists = playListService.getAll();
        model.addAttribute("playLists", playLists);
        return "playLists";
    }

    @PostMapping("/playLists")
    public String create(Principal principal, String name, Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        playListService.add(user, name);
        return "redirect:/playLists";
    }
}
