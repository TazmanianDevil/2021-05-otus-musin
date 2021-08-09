package ru.otus.homework.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
