package ru.otus.homework.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.jpa.Genre;

import java.util.Optional;

public interface JpaGenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> getByName(String name);
}