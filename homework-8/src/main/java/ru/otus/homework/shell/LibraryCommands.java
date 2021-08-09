package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.SaveCommentRequest;
import ru.otus.homework.model.UpdateCommentRequest;
import ru.otus.homework.service.LibraryService;
import ru.otus.homework.service.UserInputService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {

    private final UserInputService inputService;
    private final LibraryService libraryService;

    @ShellMethod(value = "Get book by id", key = {"book", "b", "gb", "get book"})
    public Book getBook(@ShellOption String id) {
        return libraryService.findBookById(id);
    }

    @ShellMethod(value = "Get all books", key = {"books", "bs", "gbs", "get books"})
    public List<Book> getAllBooks() {
        return libraryService.findAllBooks();
    }

    @ShellMethod(value = "Add book to Library", key = {"create book", "cb"})
    public String addBook() {
        Book book = inputService.getBookForCreate();
        Book createdBook = libraryService.saveBook(book);
        return "Book has been added. New book id is  " + createdBook.getId();
    }

    @ShellMethod(value = "Delete book from library", key = {"delete book", "db"})
    public String deleteBook(@ShellOption String id) {
        libraryService.deleteBookById(id);
        return "Book has been deleted!";
    }

    @ShellMethod(value = "Edit book in Library", key = {"update book", "ub"})
    public String editBook() {
        Book book = inputService.getBookForUpdate();
        libraryService.saveBook(book);
        return "Book has been updated!";
    }

    @ShellMethod(value = "Add comment to book", key = {"add comment", "ac"})
    public String addComment() {
        SaveCommentRequest request = inputService.getCommentForCreate();
        libraryService.saveComment(request);
        return "Comment has been saved!";
    }

    @ShellMethod(value = "Get all comments by book id", key = {"comments", "cs", "gcs", "get comments"})
    public List<Comment> getAllComments(@ShellOption String bookId) {
        return libraryService.findCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Delete comment for book", key = {"delete comment", "dc"})
    public String deleteComment(@ShellOption String bookId, @ShellOption String commentId) {
        libraryService.deleteCommentByBookId(bookId, commentId);
        return "Comment has been deleted!";
    }

    @ShellMethod(value = "Delete all comments for book", key = {"delete all comments", "dac"})
    public String deleteAllCommentsByBookId(@ShellOption String bookId) {
        libraryService.deleteAllCommentsByBookId(bookId);
        return "Comments have been deleted!";
    }

    @ShellMethod(value = "Edit comment for book", key = {"update comment", "uc"})
    public String updateCommentByBookId() {
        UpdateCommentRequest request = inputService.getCommentForUpdate();
        libraryService.updateComment(request);
        return "Comment has been updated!";
    }
}
