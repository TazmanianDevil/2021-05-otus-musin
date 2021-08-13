package ru.otus.homework.service;

import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.CreateBookRequest;
import ru.otus.homework.dto.EditBookRequest;
import ru.otus.homework.dto.GenreDto;

import java.util.List;

public interface LibraryService {

    BookDto getBookById(long id);

    List<BookDto> getAllBooks();

    List<GenreDto> getAllGenres();

    BookDto createBook(CreateBookRequest request);

    void deleteBookById(long id);

    BookDto updateBook(EditBookRequest request);

}
