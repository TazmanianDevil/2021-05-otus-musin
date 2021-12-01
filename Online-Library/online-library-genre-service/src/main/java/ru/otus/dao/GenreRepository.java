package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}