package ru.otus.homework.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
