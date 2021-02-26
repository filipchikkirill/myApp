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

    @Value("${upload.audio.path}")
    private String uploadPath;

    public void addAudio(String name, String author, User user, MultipartFile file) throws IOException {
        Audio audio = new Audio();
        audio.setName(name);
        audio.setAuthor(author);
        audio.setUser(user);
        audio.setDateAdd( new Date(System.currentTimeMillis()));
        saveFile(audio, file);

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

    private void saveFile(Audio audio,
                          MultipartFile file
    ) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "_" + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            audio.setFilename(resultFilename);
        }
    }
}
