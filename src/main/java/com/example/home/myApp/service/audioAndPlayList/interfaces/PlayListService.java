package com.example.home.myApp.service.audioAndPlayList.interfaces;

import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.domain.audioAndPlayList.Audio;
import com.example.home.myApp.domain.audioAndPlayList.PlayList;

public interface PlayListService {
    Iterable<PlayList> getAll();

    PlayList getById(Long id);

    void savePlayList(PlayList playList);

    void addAudioToPlayList(Audio audio, PlayList playList);

    void add(User user, String name);
}
