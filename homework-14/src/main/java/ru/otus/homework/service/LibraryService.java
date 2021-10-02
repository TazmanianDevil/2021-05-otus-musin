package ru.otus.homework.service;

import ru.otus.homework.model.jpa.Book;

import java.util.List;

public interface LibraryService {
    List<Book> getAll();
    Book saveBook(Book book);
}
