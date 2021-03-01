package com.example.home.myApp.service.dirsAndFile;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.home.myApp.domain.dirsAndFiles.BaseDir;
import com.example.home.myApp.domain.dirsAndFiles.Dir;
import com.example.home.myApp.domain.dirsAndFiles.FileCustom;
import com.example.home.myApp.repository.dirsAndFiles.BaseDirRepo;
import com.example.home.myApp.repository.dirsAndFiles.DirRepo;
import com.example.home.myApp.repository.dirsAndFiles.FileCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.nio.file.Files.newDirectoryStream;

@Component
public class DirPathReader {

    @Autowired
    private BaseDirRepo baseDirRepo;
    @Autowired
    private DirRepo dirRepo;
    @Autowired
    private FileCustomRepo fileCustomRepo;

    public void read(String dirPath){
        BaseDir baseDir = new BaseDir();
        baseDir.setPath(dirPath.strip());
        baseDir.setDateAdd(new Date(System.currentTimeMillis()));
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Paths.get(dirPath.strip()))) {
            for (Path path : directoryStream) {
                if(path.toAbsolutePath().toFile().isDirectory()) {
                    Dir dir = new Dir();

                    dir.setName(path.getFileName().toString());
                    dir.setBaseDir(baseDir);

                    dirRepo.save(dir);
                } else {
                    FileCustom file = new FileCustom();

                    file.setName(path.getFileName().toString());
                    file.setSize(path.toFile().length());
                    file.setBaseDir(baseDir);

                    fileCustomRepo.save(file);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}