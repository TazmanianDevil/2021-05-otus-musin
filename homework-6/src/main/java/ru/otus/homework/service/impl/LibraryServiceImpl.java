package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.exception.WrongBookException;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.CommentService;
import ru.otus.homework.service.LibraryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final CommentService commentService;
    private final BookService bookService;

    @Transactional
    @Override
    public Comment createComment(Comment comment) {
        final Book book = bookService.getById(comment.getBook().getId());
        if (book == null) {
            throw new WrongBookException();
        }
        comment.setBook(book);
        return commentService.create(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(long id) {
        return bookService.getById(id);
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @Transactional
    @Override
    public Book createBook(Book book) {
        return bookService.create(book);
    }

    @Transactional
    @Override
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Book updateBook(Book book) {
        return bookService.update(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getCommentById(long id) {
        return commentService.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAllCommentsByBookId(long bookId) {
        final List<Comment> comments = bookService.getById(bookId).getComments();
        comments.size();
        return comments;
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        commentService.deleteById(id);
    }

    @Transactional
    @Override
    public Comment updateComment(Comment comment) {
        return commentService.update(comment);
    }
}
