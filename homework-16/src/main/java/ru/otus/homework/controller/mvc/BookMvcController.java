package ru.otus.homework.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.dto.SaveBookRequest;
import ru.otus.homework.service.LibraryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookMvcController {

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
        return "saveBook";
    }

    @PostMapping(value = "/books/save", params = "action=save")
    public String saveBook(SaveBookRequest request) {
        if (request.getId() == 0) {
            libraryService.createBook(request);
        } else {
            libraryService.updateBook(request);
        }
        return "redirect:/books";
    }

    @PostMapping(value = "/books/save", params = "action=cancel")
    public String cancelBookSaving() {
        return "redirect:/books";
    }

    @GetMapping("/books/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        BookDto book = libraryService.getBookById(id);
        model.addAttribute("book", book);
        final List<GenreDto> genres = libraryService.getAllGenres();
        model.addAttribute("genres", genres);
        return "saveBook";
    }

    @GetMapping("books/delete")
    public String delete(@RequestParam("id") long id) {
        libraryService.deleteBookById(id);
        return "redirect:/books";
    }
}
