package ru.otus.homework.service;

import ru.otus.homework.model.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> getById(long id);

    Optional<Author> getByFullName(String fullName);

    Author create(String fullName);
}