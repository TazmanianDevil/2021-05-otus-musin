package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.Genre;
import ru.otus.homework.model.exception.WrongAuthorException;
import ru.otus.homework.model.exception.WrongBookException;
import ru.otus.homework.model.exception.WrongGenreException;
import ru.otus.homework.service.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final CommentService commentService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        final Optional<Book> book = bookService.getById(comment.getBook().getId());
        if (book.isEmpty()) {
            throw new WrongBookException();
        }
        comment.setBook(book.get());
        return commentService.create(comment);
    }

    @Override
    @Transactional
    public Book getBookById(long id) {
        return bookService.getById(id)
                .orElseThrow(WrongBookException::new);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @Transactional
    @Override
    public Book createBook(Book book) {
        Optional<Author> authorOptional = authorService.getById(book.getAuthor().getId());
        if (authorOptional.isEmpty()) {
            throw new WrongAuthorException();
        }
        Optional<Genre> genreOptional = genreService.getById(book.getGenre().getId());
        if (genreOptional.isEmpty()) {
            throw new WrongGenreException();
        }

        book.setAuthor(authorOptional.get());
        book.setGenre(genreOptional.get());

        return bookService.create(book);
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        return bookService.update(book);
    }

    @Transactional
    @Override
    public Comment getCommentById(long id) {
        return commentService.getById(id)
                .orElseThrow();
    }

    @Transactional
    @Override
    public List<Comment> getAllCommentsByBookId(long bookId) {
        return commentService.getAllByBookId(bookId);
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
