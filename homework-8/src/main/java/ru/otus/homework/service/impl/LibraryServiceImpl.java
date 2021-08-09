package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.*;
import ru.otus.homework.model.exception.AuthorNotFoundException;
import ru.otus.homework.model.exception.BookNotFoundException;
import ru.otus.homework.model.exception.GenreNotFoundException;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.LibraryService;
import ru.otus.homework.util.ModelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

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
        bookService.deleteById(id);
    }

    public void saveComment(SaveCommentRequest request) {
        final Optional<Book> bookOptional = bookService.findById(request.getBookId());
        ModelUtil.checkEntity(bookOptional, BookNotFoundException.class);
        final Book book = bookOptional.get();
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }
        book.getComments().add(new Comment(request.getText()));
        bookService.save(book);
    }

    @Override
    public List<Comment> findCommentsByBookId(String bookId) {
        final Optional<Book> bookOptional = bookService.findById(bookId);
        ModelUtil.checkEntity(bookOptional, BookNotFoundException.class);
        return bookOptional.get().getComments();
    }

    @Override
    public void deleteCommentByBookId(String bookId, String commentText) {
        final Optional<Book> bookOptional = bookService.findById(bookId);
        ModelUtil.checkEntity(bookOptional, BookNotFoundException.class);
        final Book book = bookOptional.get();
        book.getComments()
                .removeIf(comment -> StringUtils.equals(commentText, comment.getText()));
        bookService.save(book);
    }

    @Override
    public void deleteAllCommentsByBookId(String id) {
        final Optional<Book> bookOptional = bookService.findById(id);
        ModelUtil.checkEntity(bookOptional, BookNotFoundException.class);
        final Book book = bookOptional.get();
        book.getComments().clear();
        bookService.save(book);
    }

    @Override
    public void updateComment(UpdateCommentRequest request) {
        final Optional<Book> bookOptional = bookService.findById(request.getBookId());
        ModelUtil.checkEntity(bookOptional, BookNotFoundException.class);
        final Book book = bookOptional.get();
        if (book.getComments().removeIf(comment -> StringUtils.equals(request.getOldText(), comment.getText()))) {
            book.getComments().add(new Comment(request.getNewText()));
        }
        bookService.save(book);
    }
}
