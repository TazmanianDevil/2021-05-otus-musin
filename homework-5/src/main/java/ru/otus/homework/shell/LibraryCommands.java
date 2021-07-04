package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.model.Book;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.UserInputService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {

    private final BookService bookService;
    private final UserInputService inputService;

    @ShellMethod(value = "Get book by id", key = {"book", "b", "gb", "get book"})
    public Book getBook(@ShellOption long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Get all", key = {"books", "bs", "gbs", "get books"})
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Add book to Library", key = {"insert", "insert book", "ib"})
    public String addBook() {
        Book book = inputService.getBookForCreate();
        long id = bookService.insert(book);
        return "Book has been added, id is " + id;
    }

    @ShellMethod(value = "Delete book from library", key = {"delete", "db", "del"})
    public String deleteBook(@ShellOption long id) {
        boolean deleted = bookService.deleteById(id);
        return deleted ? "Book has been deleted!" : "Book hasn't been deleted!";
    }

    @ShellMethod(value = "Edit book in Library", key = {"update", "update book", "ub"})
    public String editBook() {
        Book book = inputService.getBookForUpdate();
        boolean updated = bookService.update(book);
        return updated ? "Book has been updated!" : "Book hasn't been updated!";
    }
}
