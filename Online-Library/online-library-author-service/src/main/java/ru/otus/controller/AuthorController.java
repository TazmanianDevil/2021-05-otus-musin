package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.model.Author;
import ru.otus.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/api/authors/{id}")
    public Author getById(@PathVariable("id") long id) {
        return authorService.getById(id);
    }

    @PostMapping("/api/authors")
    public Author save(@RequestBody Author author) {
        return authorService.create(author.getFullName());
    }
}

