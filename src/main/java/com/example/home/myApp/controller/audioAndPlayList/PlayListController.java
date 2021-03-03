package com.example.home.myApp.controller.audioAndPlayList;

import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.domain.audioAndPlayList.Audio;
import com.example.home.myApp.domain.audioAndPlayList.PlayList;
import com.example.home.myApp.service.audioAndPlayList.interfaces.AudioService;
import com.example.home.myApp.service.audioAndPlayList.interfaces.PlayListService;
import com.example.home.myApp.service.user.interfaces.UserService;
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
    private PlayListService playListServiceImpl;
    @Autowired
    private AudioService audioServiceImpl;
    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/playList")
    public String playlist(Principal principal,
                           @RequestParam(name="id", defaultValue="-1", required=false) String id,
                           Model model) {
        if (id.equals("-1")) {
            return "redirect:/playLists";
        }

        PlayList playList = playListServiceImpl.getById(Long.parseLong(id));
        model.addAttribute("playList", playList);

        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
        if (user.equals(playList.getUser())) {
            model.addAttribute("owner", true);
            Iterable<Audio> audios = audioServiceImpl.getAll();
            model.addAttribute("audios", audios);
        }
        return "playList";
    }

    @PostMapping("/playList")
    public String addAudio(String audio, String listId) {
        Audio theAudio = audioServiceImpl.getAudioById(Long.parseLong(audio));
        PlayList thePlayList = playListServiceImpl.getById(Long.parseLong(listId));

        playListServiceImpl.addAudioToPlayList(
                theAudio,
                thePlayList);

        return "redirect:/playList?id=" + listId;
    }

    @GetMapping("/playLists")
    public String playLists(Model model) {
        Iterable<PlayList> playLists = playListServiceImpl.getAll();
        model.addAttribute("playLists", playLists);
        return "playLists";
    }

    @PostMapping("/playLists")
    public String create(Principal principal, String name, Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
        playListServiceImpl.add(user, name);
        return "redirect:/playLists";
    }
}
