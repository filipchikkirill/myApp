package com.example.home.myApp.controller;

import com.example.home.myApp.domain.dirsAndFiles.BaseDir;
import com.example.home.myApp.repository.dirsAndFiles.BaseDirRepo;
import com.example.home.myApp.service.dirsAndFile.DirPathReader;
import com.example.home.myApp.service.dirsAndFile.NaturalOrderComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FileAndDirsController {
    @Autowired
    private DirPathReader dirPathReader;
    @Autowired
    private BaseDirRepo baseDirRepo;
    @Autowired
    private NaturalOrderComparator naturalOrderComparator;

    @GetMapping("/dirs_and_files")
    public String dirs_and_files(Model model)
    {
        List<BaseDir> baseDirList = baseDirRepo.findAll();
        model.addAttribute("baseDirs", baseDirList);
        return "dirs_and_files";
    }

    @PostMapping("/dirs_and_files")
    public String add(String path, Model model)
    {
        String message = dirPathReader.read(path);
        model.addAttribute("message", message);
        return dirs_and_files(model);
    }

    @GetMapping("/dirs_and_files/{baseDir}")
    public String getContent(
            @PathVariable BaseDir baseDir,
            Model model
    ) {
        baseDir.getDirs().sort(naturalOrderComparator);
        baseDir.getFiles().sort(naturalOrderComparator);
        model.addAttribute("baseDir", baseDir);
        return "dirs_and_files_content";
    }
}
