package ru.otus.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.otus.homework.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();
}
