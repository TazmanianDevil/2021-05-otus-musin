package ru.otus.homework.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.service.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final LibraryService libraryService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        libraryService.deleteBookById(id);
    }

}
