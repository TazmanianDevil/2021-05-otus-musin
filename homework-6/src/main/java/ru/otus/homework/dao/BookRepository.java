package ru.otus.homework.dao;

import ru.otus.homework.model.Book;

import java.util.List;

public interface BookRepository {
    Book create(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    Book update(Book book);
}
