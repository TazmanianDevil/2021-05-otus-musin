package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.model.Genre;
import ru.otus.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/api/genres")
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/api/genres/{id}")
    public Genre getById(@PathVariable("id") long id) {
        return genreService.getById(id);
    }

    @PostMapping("/api/genres")
    public Genre save(@RequestBody Genre author) {
        return genreService.create(author.getName());
    }
}

