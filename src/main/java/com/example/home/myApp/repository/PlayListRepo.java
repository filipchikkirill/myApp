package com.example.home.myApp.repository;

import com.example.home.myApp.domain.audio.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepo extends JpaRepository<PlayList, Long> {
    Iterable<PlayList> findByUserId(Long userId);
    Iterable<PlayList> findByName(String name);
}
