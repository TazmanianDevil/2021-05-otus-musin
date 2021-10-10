package ru.otus.homework.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.jpa.Author;

import java.util.Optional;

public interface JpaAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> getByFullName(String fullName);
}
