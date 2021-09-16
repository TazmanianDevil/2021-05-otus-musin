package ru.otus.homework.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.service.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final LibraryService libraryService;

    @GetMapping("/api/books")
    public List<BookDto> getBooks() {
        return libraryService.getAllBooks();
    }
}
