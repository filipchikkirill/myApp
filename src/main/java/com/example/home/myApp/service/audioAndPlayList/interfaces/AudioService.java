package com.example.home.myApp.service.audioAndPlayList.interfaces;

import com.example.home.myApp.domain.User;
import com.example.home.myApp.domain.audioAndPlayList.Audio;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AudioService {
    void addAudio(String name, String author, User user, MultipartFile file) throws IOException;

    Audio getAudioById(Long id);

    Iterable<Audio> getAll();

    Iterable<Audio> getUserAudios(User user);
}
