package ru.otus.homework.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.model.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
