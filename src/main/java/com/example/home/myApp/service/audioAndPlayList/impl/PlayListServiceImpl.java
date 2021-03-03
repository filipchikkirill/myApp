package com.example.home.myApp.service.audioAndPlayList.impl;

import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.domain.audioAndPlayList.Audio;
import com.example.home.myApp.domain.audioAndPlayList.PlayList;
import com.example.home.myApp.repository.audioAndPlayList.PlayListRepo;
import com.example.home.myApp.service.audioAndPlayList.interfaces.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayListServiceImpl implements PlayListService {
    @Autowired
    private PlayListRepo playListRepo;

    @Override public Iterable<PlayList> getAll() {
        return playListRepo.findAll();
    }

    @Override public PlayList getById(Long id) {
        return playListRepo.getOne(id);
    }
    @Override public void savePlayList(PlayList playList) {
        playListRepo.save(playList);
    }

    @Override public void addAudioToPlayList(Audio audio,PlayList playList) {
        playList.addAudio(audio);
        savePlayList(playList);
    }

    @Override public void add(User user, String name) {
        PlayList playList = new PlayList();
        playList.setUser(user);
        playList.setName(name);
        playList.setDateAdd( new Date(System.currentTimeMillis()));

        playListRepo.save(playList);
    }
}
