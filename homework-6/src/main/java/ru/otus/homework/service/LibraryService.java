package ru.otus.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

import java.util.List;

public interface LibraryService {

    @Transactional
    Comment createComment(Comment comment);

    @Transactional
    Book getBookById(long id);

    @Transactional
    List<Book> getAllBooks();

    @Transactional
    Book createBook(Book book);

    @Transactional
    void deleteBookById(long id);

    @Transactional
    Book updateBook(Book book);

    @Transactional
    Comment getCommentById(long id);

    @Transactional
    List<Comment> getAllCommentsByBookId(long bookId);

    @Transactional
    void deleteCommentById(long id);

    @Transactional
    Comment updateComment(Comment comment);
}
