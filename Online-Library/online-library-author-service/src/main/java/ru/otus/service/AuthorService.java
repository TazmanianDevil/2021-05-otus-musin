package ru.otus.service;

import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author getById(long id);

    Optional<Author> getByFullName(String fullName);

    Author create(String fullName);

    List<Author> getAll();
}