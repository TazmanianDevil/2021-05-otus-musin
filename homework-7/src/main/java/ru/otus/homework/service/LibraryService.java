package ru.otus.homework.service;

import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

import java.util.List;

public interface LibraryService {
    Comment createComment(Comment comment);

    Book getBookById(long id);

    List<Book> getAllBooks();

    Book createBook(Book book);

    void deleteBookById(long id);

    Book updateBook(Book book);

    Comment getCommentById(long id);

    List<Comment> getAllCommentsByBookId(long bookId);

    void deleteCommentById(long id);

    Comment updateComment(Comment comment);
}
