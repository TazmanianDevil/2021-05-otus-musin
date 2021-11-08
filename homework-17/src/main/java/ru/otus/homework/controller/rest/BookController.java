package ru.otus.homework.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.service.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j

public class BookController {

    private final LibraryService libraryService;

    @GetMapping(value = "/api/books", produces = "application/json")
    public List<BookDto> getAll() {
        final List<BookDto> books = libraryService.getAllBooks();
        log.info("books: {}", books);
        return books;
    }
}
