package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.Genre;
import ru.otus.homework.model.exception.AuthorNotFoundException;
import ru.otus.homework.model.exception.BookNotFoundException;
import ru.otus.homework.model.exception.CommentNotFoundException;
import ru.otus.homework.model.exception.GenreNotFoundException;
import ru.otus.homework.service.*;
import ru.otus.homework.util.ModelUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @Override
    public Book findBookById(String id) {
        final Optional<Book> bookOptional = bookService.findById(id);
        ModelUtil.checkEntity(bookOptional, BookNotFoundException.class);
        return bookOptional.get();
    }

    @Override
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @Override
    public Book saveBook(Book book) {
        final Optional<Author> authorOptional = authorService.findById(book.getAuthor().getId());
        ModelUtil.checkEntity(authorOptional, AuthorNotFoundException.class);
        final Optional<Genre> genreOptional = genreService.findById(book.getGenre().getId());
        ModelUtil.checkEntity(genreOptional, GenreNotFoundException.class);
        book.setGenre(genreOptional.get());
        book.setAuthor(authorOptional.get());
        return bookService.save(book);
    }

    @Override
    public void deleteBookById(String id) {
        commentService.deleteByBookId(id);
        bookService.deleteById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        final Optional<Book> bookOptional = bookService.findById(comment.getBookId());
        ModelUtil.checkEntity(bookOptional, BookNotFoundException.class);
        return commentService.save(comment);
    }

    @Override
    public Comment findCommentById(String id) {
        final Optional<Comment> commentOptional = commentService.findById(id);
        ModelUtil.checkEntity(commentOptional, CommentNotFoundException.class);
        return commentOptional.get();
    }

    @Override
    public List<Comment> findCommentsByBookId(String bookId) {
        return commentService.findAllByBookId(bookId);
    }

    @Override
    public void deleteCommentById(String id) {
        commentService.deleteById(id);
    }
}
