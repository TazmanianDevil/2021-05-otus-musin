package ru.otus.homework.controller.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.SaveBookRequest;
import ru.otus.homework.service.LibraryService;
import ru.otus.homework.service.impl.LibraryStub;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j

public class BookController {

    private final LibraryService libraryService;
    private final LibraryStub libraryStub;

    @GetMapping(value = "/api/books", produces = "application/json")
    @HystrixCommand(defaultFallback = "defaultBooks")
    public List<BookDto> getAll() {
        final List<BookDto> books = libraryService.getAllBooks();
        log.info("books: {}", books);
        return books;
    }

    @PostMapping(value = "/api/books", consumes = "application/json", produces = "application/json")
    @HystrixCommand(defaultFallback = "defaultCreatBook")
    public BookDto create(@RequestBody SaveBookRequest request) {
        final BookDto book = libraryService.createBook(request);
        log.info("book created: {}", book);
        return book;
    }

    @GetMapping(value = "/api/books/{id}", produces = "application/json")
    @HystrixCommand(defaultFallback = "defaultBook")
    public BookDto getById(@PathVariable("id") long id) {
        final BookDto book = libraryService.getBookById(id);
        log.info("book: {}", book);
        return book;
    }

    @PutMapping(value = "/api/books", consumes = "application/json", produces = "application/json")
    @HystrixCommand(defaultFallback = "defaultCreatBook")
    public BookDto update(@RequestBody SaveBookRequest request) {
        final BookDto book = libraryService.updateBook(request);
        log.info("books updated: {}", book);
        return book;
    }

    @DeleteMapping(value = "/api/books/{id}")
    public void delete(@PathVariable long id) {
        libraryService.deleteBookById(id);
        log.info("book deleted: {}", id);
    }

    private BookDto defaultBook() {
        return libraryStub.defaultBook();
    }

    private List<BookDto> defaultBooks() {
        return libraryStub.defaultBooks();
    }

    private BookDto defaultCreatBook() {
        return new BookDto(0, "Book not created", "Default author", "Default genre");
    }
}
