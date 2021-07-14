package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.service.LibraryService;
import ru.otus.homework.service.UserInputService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {

    //    private final BookService bookService;
    private final UserInputService inputService;
    //    private final CommentService commentService;
    private final LibraryService libraryService;

    @ShellMethod(value = "Get book by id", key = {"book", "b", "gb", "get book"})
    public Book getBook(@ShellOption long id) {
        return libraryService.getBookById(id);
    }

    @ShellMethod(value = "Get all books", key = {"books", "bs", "gbs", "get books"})
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @ShellMethod(value = "Add book to Library", key = {"create book", "cb"})
    public String addBook() {
        Book book = inputService.getBookForCreate();
        Book createdBook = libraryService.createBook(book);
        return "Book has been added. New book id is  " + createdBook.getId();
    }

    @ShellMethod(value = "Delete book from library", key = {"delete book", "db"})
    public String deleteBook(@ShellOption long id) {
        libraryService.deleteBookById(id);
        return "Book has been deleted!";
    }

    @ShellMethod(value = "Edit book in Library", key = {"update book", "ub"})
    public String editBook() {
        Book book = inputService.getBookForUpdate();
        libraryService.updateBook(book);
        return "Book has been updated!";
    }

    @ShellMethod(value = "Add comment to book", key = {"add comment", "ac"})
    public String addComment() {
        Comment comment = inputService.getCommentForCreate();
        libraryService.createComment(comment);
        return "Comment has been added. New comment id is  " + comment.getId();
    }

    @ShellMethod(value = "Get comment by id", key = {"comment", "c", "gc", "get comment"})
    public Comment getComment(@ShellOption long id) {
        return libraryService.getCommentById(id);
    }

    @ShellMethod(value = "Get all comments by book id", key = {"comments", "cs", "gcs", "get comments"})
    public List<Comment> getAllComments(@ShellOption long bookId) {
        return libraryService.getAllCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Delete comment for book", key = {"delete comment", "dc"})
    public String deleteComment(@ShellOption long id) {
        libraryService.deleteCommentById(id);
        return "Comment has been deleted!";
    }

    @ShellMethod(value = "Edit comment for book", key = {"update comment", "uc"})
    public String editComment() {
        Comment comment = inputService.getCommentForUpdate();
        libraryService.updateComment(comment);
        return "Comment has been updated!";
    }
}
