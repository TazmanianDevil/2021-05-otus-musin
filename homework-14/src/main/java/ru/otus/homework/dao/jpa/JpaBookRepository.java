package ru.otus.homework.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.jpa.Book;

import java.util.Optional;

public interface JpaBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
