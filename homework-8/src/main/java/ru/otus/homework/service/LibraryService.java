package ru.otus.homework.service;

import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.SaveCommentRequest;
import ru.otus.homework.model.UpdateCommentRequest;

import java.util.List;

public interface LibraryService {

    Book findBookById(String id);

    List<Book> findAllBooks();

    Book saveBook(Book book);

    void deleteBookById(String id);

    void saveComment(SaveCommentRequest request);

    List<Comment> findCommentsByBookId(String bookId);

    void deleteCommentByBookId(String bookId, String commentText);

    void deleteAllCommentsByBookId(String id);

    void updateComment(UpdateCommentRequest request);
}
