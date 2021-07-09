package ru.otus.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Book;

import java.util.List;


public interface BookService {
    @Transactional
    Book getById(long id);

    @Transactional
    void deleteById(long id);

    @Transactional
    List<Book> getAll();

    @Transactional
    Book create(Book book);

    @Transactional
    Book update(Book book);
}
