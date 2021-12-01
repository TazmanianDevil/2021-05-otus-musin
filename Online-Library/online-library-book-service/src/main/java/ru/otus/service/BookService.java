package ru.otus.service;


import ru.otus.model.Book;

import java.util.List;

public interface BookService {
    Book getById(long id);

    List<Book> getAll();

    Book create(Book book);
}
