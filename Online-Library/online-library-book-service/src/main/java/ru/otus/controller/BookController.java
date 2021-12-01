package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.model.Book;
import ru.otus.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService BookService;

    @GetMapping("/api/books")
    public List<Book> getAll() {
        return BookService.getAll();
    }

    @GetMapping("/api/books/{id}")
    public Book getById(@PathVariable("id") long id) {
        return BookService.getById(id);
    }

    @PostMapping("/api/books")
    public Book save(@RequestBody Book book) {
        return BookService.create(book);
    }
}

