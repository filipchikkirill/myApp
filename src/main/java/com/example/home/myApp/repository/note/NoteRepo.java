package com.example.home.myApp.repository.note;

import com.example.home.myApp.domain.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepo extends JpaRepository<Note, Long>
{
    List<Note> findByUserId(Long userId);
}