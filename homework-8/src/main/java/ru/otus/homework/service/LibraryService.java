package ru.otus.homework.service;

import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

import java.util.List;

public interface LibraryService {

    Book findBookById(String id);

    List<Book> findAllBooks();

    Book saveBook(Book book);

    void deleteBookById(String id);

    Comment saveComment(Comment comment);

    Comment findCommentById(String id);

    List<Comment> findCommentsByBookId(String bookId);

    void deleteCommentById(String id);
}
