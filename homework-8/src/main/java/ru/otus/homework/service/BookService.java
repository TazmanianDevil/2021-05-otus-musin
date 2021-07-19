package ru.otus.homework.service;

import ru.otus.homework.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Optional<Book> findById(String id);

    List<Book> findAllBooks();

    void deleteById(String id);
}
