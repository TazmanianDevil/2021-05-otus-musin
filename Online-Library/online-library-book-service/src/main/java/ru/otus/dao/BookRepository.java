package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
