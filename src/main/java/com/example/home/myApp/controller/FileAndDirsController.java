package com.example.home.myApp.controller;

import com.example.home.myApp.domain.dirsAndFiles.BaseDir;
import com.example.home.myApp.repository.dirsAndFiles.BaseDirRepo;
import com.example.home.myApp.repository.dirsAndFiles.DirRepo;
import com.example.home.myApp.service.dirsAndFile.DirPathReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FileAndDirsController {
    @Autowired
    private DirPathReader dirPathReader;
    @Autowired
    private BaseDirRepo baseDirRepo;


    @GetMapping("/dirs_and_files")
    public String dirs_and_files(Model model)
    {
        List<BaseDir> baseDirList = baseDirRepo.findAll();
        model.addAttribute("baseDirs", baseDirList);
        return "dirs_and_files";
    }

    @PostMapping("/dirs_and_files")
    public String add(String path)
    {
        dirPathReader.read(path);
        return "redirect:/dirs_and_files";
    }
}
