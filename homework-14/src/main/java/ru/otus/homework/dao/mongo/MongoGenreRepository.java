package ru.otus.homework.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.model.mongo.Genre;

public interface MongoGenreRepository extends MongoRepository<Genre, String> {
}
