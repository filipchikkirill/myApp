package com.example.home.myApp.repository.audioAndPlayList;

import com.example.home.myApp.domain.audioAndPlayList.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepo extends JpaRepository<Audio, Long> {

    Iterable<Audio> findByUserName(String user);
}
