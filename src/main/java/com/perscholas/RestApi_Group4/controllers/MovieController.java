package com.perscholas.RestApi_Group4.controllers;

import com.perscholas.RestApi_Group4.repository.entities.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MovieController {
    @GetMapping
    public String getMoviePage(Model model) {
        model.addAttribute("myMovie",new Movie());
        return "movies";
    }
}
