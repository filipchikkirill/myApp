package com.example.home.myApp.service.audioAndPlayList.impl;

import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.domain.audioAndPlayList.Audio;
import com.example.home.myApp.repository.audioAndPlayList.AudioRepo;
import com.example.home.myApp.service.audioAndPlayList.interfaces.AudioService;
import com.example.home.myApp.utils.FileSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioRepo audioRepo;
    @Autowired
    private FileSaveService fileSaveService;

    @Override public void addAudio(String name, String author, User user, MultipartFile file) throws IOException {
        Audio audio = new Audio();
        audio.setName(name);
        audio.setAuthor(author);
        audio.setUser(user);
        audio.setDateAdd( new Date(System.currentTimeMillis()));
        if (!fileSaveService.saveFile(audio, file)) {
            return;
        }

        audioRepo.save(audio);
    }

    @Override public Audio getAudioById(Long id) {
        return audioRepo.getOne(id);
    }

    @Override public Iterable<Audio> getAll() {
        return audioRepo.findAll();
    }
    @Override public Iterable<Audio> getUserAudios(User user) {
        return audioRepo.findByUserName(user.getName());
    }

}
