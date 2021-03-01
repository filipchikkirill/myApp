package com.example.home.myApp.repository.dirsAndFiles;

import com.example.home.myApp.domain.dirsAndFiles.BaseDir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDirRepo extends JpaRepository<BaseDir, Long> {
}
