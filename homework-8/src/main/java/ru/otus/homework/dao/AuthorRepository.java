package ru.otus.homework.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.model.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
