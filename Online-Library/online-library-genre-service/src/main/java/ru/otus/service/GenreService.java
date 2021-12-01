package ru.otus.service;


import ru.otus.model.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    List<Genre> getAll();

    Genre create(String genreName);
}
