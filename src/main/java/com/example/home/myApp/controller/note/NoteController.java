package com.example.home.myApp.controller.note;

import com.example.home.myApp.domain.note.Note;
import com.example.home.myApp.domain.user.User;
import com.example.home.myApp.repository.note.NoteRepo;
import com.example.home.myApp.service.user.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class NoteController
{
    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/notes")
    public String notes(Principal principal, Model model)
    {
        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
        List<Note> notes = noteRepo.findByUserId(user.getId());
        model.addAttribute("notes", notes);
        model.addAttribute("user", user);

        return "notes";
    }

    @PostMapping("/addnote")
    public String addNote(Principal principal, String title, String note)
    {
        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());

        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setNote(note);
        newNote.setUserId(user.getId());

        noteRepo.save(newNote);

        return "redirect:/notes";
    }
}
