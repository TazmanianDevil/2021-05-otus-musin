package ru.otus.homework.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.model.mongo.Author;

public interface MongoAuthorRepository extends MongoRepository<Author, String> {
}
