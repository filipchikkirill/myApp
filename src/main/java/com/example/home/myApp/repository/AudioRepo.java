package com.example.home.myApp.repository;

import com.example.home.myApp.domain.audio.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepo extends JpaRepository<Audio, Long> {
    Iterable<Audio> findByName(String name);
    Iterable<Audio> findByAuthor(String author);
    Iterable<Audio> findByUserName(String user);
}
