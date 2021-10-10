package ru.otus.homework.service;

import ru.otus.homework.model.jpa.Book;

public interface BookTransformer {
    Book transform(ru.otus.homework.model.mongo.Book book);
}
