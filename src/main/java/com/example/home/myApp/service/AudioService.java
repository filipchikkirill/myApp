package com.example.home.myApp.service;

import com.example.home.myApp.domain.User;
import com.example.home.myApp.domain.audio.Audio;
import com.example.home.myApp.repository.AudioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class AudioService {
    @Autowired
    private AudioRepo audioRepo;
    @Autowired
    private FileSaveService fileSaveService;

    public void addAudio(String name, String author, User user, MultipartFile file) throws IOException {
        Audio audio = new Audio();
        audio.setName(name);
        audio.setAuthor(author);
        audio.setUser(user);
        audio.setDateAdd( new Date(System.currentTimeMillis()));
        fileSaveService.saveFile(audio, file);

        audioRepo.save(audio);
    }

    public void saveAudio(Audio audio) {
        audioRepo.save(audio);
    }

    public Audio getAudioById(Long id) {
        return audioRepo.getOne(id);
    }

    public Iterable<Audio> getAll() {
        return audioRepo.findAll();
    }
    public Iterable<Audio> getUserAudios(User user) {
        return audioRepo.findByUserName(user.getName());
    }

}
