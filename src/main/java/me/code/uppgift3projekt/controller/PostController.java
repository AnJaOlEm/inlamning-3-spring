package me.code.uppgift3projekt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PostController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Blogg");
        return "home";
    }
}
