package ru.otus.homework.service;

import ru.otus.homework.model.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(String id);
}
