package com.example.home.myApp.controller;

import com.example.home.myApp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(String name, String username, String password, Model model) {
        if (!registrationService.addUser(name, username, password)) {
            model.addAttribute("userError", "User exists! or password is empty!");
            return "registration";
        }
        return "redirect:/login";
    }
}