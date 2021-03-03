package com.example.home.myApp.repository.audioAndPlayList;

import com.example.home.myApp.domain.audioAndPlayList.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepo extends JpaRepository<PlayList, Long> {

}
