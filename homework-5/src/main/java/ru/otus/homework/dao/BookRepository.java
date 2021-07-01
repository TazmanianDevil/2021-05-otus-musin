package ru.otus.homework.dao;

import ru.otus.homework.model.Book;

import java.util.List;

public interface BookRepository {
    long insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    boolean deleteById(long id);

    boolean update(Book book);
}
