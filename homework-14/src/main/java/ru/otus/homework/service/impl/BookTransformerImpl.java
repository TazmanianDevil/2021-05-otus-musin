package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.model.jpa.Author;
import ru.otus.homework.model.jpa.Book;
import ru.otus.homework.model.jpa.Genre;
import ru.otus.homework.service.BookTransformer;

@Service
public class BookTransformerImpl implements BookTransformer {
    @Override
    public Book transform(ru.otus.homework.model.mongo.Book book) {
        Author author = new Author(book.getAuthor().getFullName());
        Genre genre = new Genre(book.getGenre().getName());
        return new Book(book.getTitle(), author, genre);
    }
}
