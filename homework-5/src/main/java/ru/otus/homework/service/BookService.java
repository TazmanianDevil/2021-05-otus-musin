package ru.otus.homework.service;

import ru.otus.homework.model.Book;

import java.util.List;

public interface BookService {

    Book getById(long id);

    boolean deleteById(long id);

    List<Book> getAll();

    long insert(Book book);

    boolean update(Book book);
}
