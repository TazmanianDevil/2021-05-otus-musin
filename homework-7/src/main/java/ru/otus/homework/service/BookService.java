package ru.otus.homework.service;

import ru.otus.homework.model.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {
    Optional<Book> getById(long id);

    void deleteById(long id);

    List<Book> getAll();

    Book create(Book book);

    Book update(Book book);
}
