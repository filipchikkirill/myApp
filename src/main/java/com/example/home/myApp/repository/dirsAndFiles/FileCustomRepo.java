package com.example.home.myApp.repository.dirsAndFiles;

import com.example.home.myApp.domain.dirsAndFiles.FileCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileCustomRepo extends JpaRepository<FileCustom, Long> {
}
