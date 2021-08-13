package ru.otus.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.SaveBookRequest;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.service.LibraryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final LibraryService libraryService;

    @GetMapping("/books")
    public String getAll(Model model) {
        final List<BookDto> books = libraryService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/create")
    public String addBook(Model model) {
        final List<GenreDto> genres = libraryService.getAllGenres();
        model.addAttribute("genres", genres);
        return "createBook";
    }

    @PostMapping(value = "/books/create", params = "action=save")
    public String saveBook(SaveBookRequest request) {
        libraryService.createBook(request);
        return "redirect:/books";
    }

    @PostMapping(value = "/books/create", params = "action=cancel")
    public String cancelBookSaving() {
        return "redirect:/books";
    }

    @GetMapping("/books/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        BookDto book = libraryService.getBookById(id);
        model.addAttribute("book", book);
        final List<GenreDto> genres = libraryService.getAllGenres();
        model.addAttribute("genres", genres);
        return "editBook";
    }

    @PostMapping(value = "/books/edit", params = "action=update")
    public String updateBook(
            SaveBookRequest request
    ) {
        libraryService.updateBook(request);
        return "redirect:/books";
    }

    @PostMapping(value = "/books/edit", params = "action=cancel")
    public String cancelBookUpdate() {
        return "redirect:/books";
    }

    @GetMapping("books/delete")
    public String delete(@RequestParam("id") long id) {
        libraryService.deleteBookById(id);
        return "redirect:/books";
    }
}
