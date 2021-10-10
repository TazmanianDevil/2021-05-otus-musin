package ru.otus.homework.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.model.mongo.Book;

public interface MongoBookRepository extends MongoRepository<Book, String> {
}
