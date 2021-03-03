package com.example.home.myApp.service.dirsAndFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;

import com.example.home.myApp.domain.dirsAndFiles.BaseDir;
import com.example.home.myApp.domain.dirsAndFiles.Dir;
import com.example.home.myApp.domain.dirsAndFiles.FileCustom;
import com.example.home.myApp.repository.dirsAndFiles.BaseDirRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.nio.file.Files.newDirectoryStream;

@Component
public class DirPathReader {
    @Autowired
    private BaseDirRepo baseDirRepo;

    public String read(String dirPath){
        try (DirectoryStream<Path> directoryStream = newDirectoryStream(Paths.get(dirPath.strip()))) {
            BaseDir baseDir = new BaseDir();
            baseDir.setPath(dirPath.strip());
            baseDir.setDateAdd(new Date(System.currentTimeMillis()));
            baseDir.setFiles(new ArrayList<>());
            baseDir.setDirs(new ArrayList<>());
            for (Path path : directoryStream) {
                if(path.toAbsolutePath().toFile().isDirectory()) {
                    Dir dir = new Dir();

                    dir.setName(path.getFileName().toString());
                    dir.setBaseDir(baseDir);

                    baseDir.getDirs().add(dir);
                } else {
                    FileCustom file = new FileCustom();

                    file.setName(path.getFileName().toString());
                    file.setSize(path.toFile().length());
                    file.setBaseDir(baseDir);

                    baseDir.getFiles().add(file);
                }
            }
            baseDirRepo.saveAndFlush(baseDir);
        }
        catch (NotDirectoryException ex) {
            return "not dir by path: " + ex.getMessage();
        }
        catch (NoSuchFileException ex) {
            return "bad path: " + ex.getMessage();
        }
        catch (IOException ex) {
            return "some problems there";
        }
        return "add DIR" + dirPath;
    }
}