package ru.otus.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.feign.AuthorRepository;
import ru.otus.feign.GenreRepository;
import ru.otus.model.Book;
import ru.otus.exception.BookNotFoundException;
import ru.otus.dao.BookRepository;
import ru.otus.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public Book getById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    @HystrixCommand(defaultFallback = "defaultCreateBook")
    public Book create(Book book) {
        checkAuthor(book.getAuthorId());
        checkGenre(book.getGenreId());
        return bookRepository.save(book);
    }

    private void checkAuthor(long authorId) {
        authorRepository.getById(authorId);
    }

    private void checkGenre(long genreId) {
        genreRepository.getById(genreId);
    }

    private Book defaultCreateBook() {
        return new Book(0, "Can't create book");
    }
}
