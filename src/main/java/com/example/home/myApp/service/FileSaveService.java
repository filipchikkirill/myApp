package com.example.home.myApp.service;

import com.example.home.myApp.domain.message.Message;
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

    public boolean saveFile(FileStorable object,
                          MultipartFile file
    ) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String originalFilename = file.getOriginalFilename();

            if (object instanceof Audio) {
                uploadPath = uploadAudioPath;

                if (!originalFilename.contains(".mp3")) {
                    return false;
                }
            } else if ( object instanceof Message) {
                uploadPath = uploadImgPath;
            }


            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "_" + originalFilename;
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            object.setFilename(resultFilename);
            return true;
        }
        return false;
    }

}
