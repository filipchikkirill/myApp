package com.example.home.myApp.service;

import com.example.home.myApp.domain.Message;
import com.example.home.myApp.domain.audio.Audio;
import com.example.home.myApp.domain.interfaces.FileStorable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileSaveService {

    @Value("${upload.audio.path}")
    private String uploadAudioPath;

    @Value("${upload.img.path}")
    private String uploadImgPath;

    @Value("${upload.path}")
    private String uploadPath;

    protected void saveFile(FileStorable object,
                          MultipartFile file
    ) throws IOException {

        String uuidFile = UUID.randomUUID().toString();

        if (object instanceof Audio) {
            uploadPath = uploadAudioPath;
        } else if ( object instanceof Message) {
            uploadPath = uploadImgPath;
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String resultFilename = uuidFile + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            object.setFilename(resultFilename);
        }
    }

}
