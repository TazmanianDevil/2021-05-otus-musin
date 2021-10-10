package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.jpa.JpaAuthorRepository;
import ru.otus.homework.dao.jpa.JpaBookRepository;
import ru.otus.homework.dao.jpa.JpaGenreRepository;
import ru.otus.homework.model.jpa.Author;
import ru.otus.homework.model.jpa.Book;
import ru.otus.homework.model.jpa.Genre;
import ru.otus.homework.service.LibraryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final JpaBookRepository bookRepository;
    private final JpaAuthorRepository authorRepository;
    private final JpaGenreRepository genreRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        final Author author = getOrCreateAuthor(book.getAuthor());
        final Genre genre = getOrCreateGenre(book.getGenre());
        final Book bookToSave = bookRepository.findByTitle(book.getTitle())
                .orElse(book);
        bookToSave.setAuthor(author);
        bookToSave.setGenre(genre);
        return bookRepository.save(bookToSave);
    }

    private Author getOrCreateAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author is absent");
        }
        final Optional<Author> authorOptional = authorRepository.getByFullName(author.getFullName());
        return authorOptional.orElseGet(() -> authorRepository.save(author));
    }

    private Genre getOrCreateGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Author is absent");
        }
        final Optional<Genre> genreOptional = genreRepository.getByName(genre.getName());
        return genreOptional
                .orElseGet(() -> genreRepository.save(genre));
    }
}
