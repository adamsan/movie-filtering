package com.codecool.movies.movies.contollers;

import com.codecool.movies.movies.models.Movie;
import com.codecool.movies.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/index")
    String index() {
        List<Movie> movies = movieService.findAll();
        movies.forEach(System.out::println);
        return "index";
    }

    @GetMapping("/")
    String index(
            @RequestParam(value = "description", required = false) String description,
            Model model
    ) {
        List<Movie> movies = description == null ? movieService.findAll() : movieService.findByDescription(description);
        movies.forEach(System.out::println);
        model.addAttribute("movies", movies);
        return "index";
    }
}
