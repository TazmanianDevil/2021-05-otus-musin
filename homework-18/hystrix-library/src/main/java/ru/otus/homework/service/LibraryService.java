package ru.otus.homework.service;

import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.dto.SaveBookRequest;

import java.util.List;

public interface LibraryService {

    BookDto getBookById(long id);

    List<BookDto> getAllBooks();

    List<GenreDto> getAllGenres();

    BookDto createBook(SaveBookRequest request);

    void deleteBookById(long id);

    BookDto updateBook(SaveBookRequest request);

}
