package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.CreateBookRequest;
import ru.otus.homework.dto.EditBookRequest;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.mapper.BookMapper;
import ru.otus.homework.mapper.GenreMapper;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;
import ru.otus.homework.model.exception.WrongBookException;
import ru.otus.homework.model.exception.WrongGenreException;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.LibraryService;
import ru.otus.homework.util.ModelUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookMapper bookMapper;
    private final GenreMapper genreMapper;

    @Override
    @Transactional
    public BookDto getBookById(long id) {
        return bookMapper.toDto(bookService.getById(id)
                .orElseThrow(WrongBookException::new));
    }

    @Override
    @Transactional
    public List<BookDto> getAllBooks() {
        final List<Book> books = bookService.getAll();
        return bookMapper.toDto(books);
    }

    @Override
    @Transactional
    public List<GenreDto> getAllGenres() {
        return genreMapper.toDto(genreService.getAll());
    }

    @Transactional
    @Override
    public BookDto createBook(CreateBookRequest request) {
        Author author = getOrCreateAuthor(request.getAuthor());

        Optional<Genre> genreOptional = genreService.getById(request.getGenre());
        ModelUtil.checkEntity(genreOptional, WrongGenreException.class);

        final Book book = new Book(request.getTitle(), author, genreOptional.get());

        return bookMapper.toDto(bookService.create(book));
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }

    @Transactional
    @Override
    public BookDto updateBook(EditBookRequest bookDto) {
        final Optional<Book> bookOptional = bookService.getById(bookDto.getId());
        ModelUtil.checkEntity(bookOptional, WrongBookException.class);

        Author author = getOrCreateAuthor(bookDto.getAuthor());

        final Optional<Genre> genreOptional = genreService.getById(bookDto.getGenre());
        ModelUtil.checkEntity(genreOptional, WrongGenreException.class);

        final Book book = bookOptional.get();
        book.setAuthor(author);
        book.setGenre(genreOptional.get());
        book.setTitle(bookDto.getTitle());
        return bookMapper.toDto(bookService.update(book));
    }

    private Author getOrCreateAuthor(String authorName) {
        return authorService.getByFullName(authorName)
                .orElseGet(() -> authorService.create(authorName));
    }
}
