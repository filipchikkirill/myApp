package com.example.home.myApp.service;

import com.example.home.myApp.domain.User;
import com.example.home.myApp.domain.audio.Audio;
import com.example.home.myApp.domain.audio.PlayList;
import com.example.home.myApp.repository.PlayListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayListService {
    @Autowired
    private PlayListRepo playListRepo;

    public Iterable<PlayList> getAll() {
        return playListRepo.findAll();
    }

    public PlayList getById(Long id) {
        return playListRepo.getOne(id);
    }
    public void savePlayList(PlayList playList) {
        playListRepo.save(playList);
    }

    public void addAudioToPlayList(Audio audio,PlayList playList) {
        playList.addAudio(audio);
        savePlayList(playList);
    }
    public Iterable<PlayList> getByName(String name) {
        return playListRepo.findByName(name);
    }

    public Iterable<PlayList> getByUser(User user) {
        return playListRepo.findByUserId(user.getId());
    }

    public void add(User user, String name) {
        PlayList playList = new PlayList();
        playList.setUser(user);
        playList.setName(name);
        playList.setDateAdd( new Date(System.currentTimeMillis()));

        playListRepo.save(playList);
    }
}
