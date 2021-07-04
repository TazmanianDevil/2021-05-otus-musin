package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.exception.NoBookFoundException;
import ru.otus.homework.mapper.BookRowMapper;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import({JdbcBookRepository.class, BookRowMapper.class})
class JdbcBookRepositoryTest {

    @Autowired
    private JdbcBookRepository bookRepository;

    @Test
    public void shouldReturnExpectedBooksCount() {
        assertThat(bookRepository.getAll()).hasSize(1);
    }

    @Test
    public void shouldInsertBook() {
        Book expectedBook = new Book(0, "Eden", new Author(1, "Stanislav Herman Lem"),
                new Genre(1, "fantasy"));

        long id = bookRepository.insert(expectedBook);
        expectedBook.setId(id);
        Book book = bookRepository.getById(id);

        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void shouldDeletePersonById() {
        boolean deleted = bookRepository.deleteById(1);
        assertThat(deleted).isTrue();

        NoBookFoundException exception = assertThrows(NoBookFoundException.class, () -> bookRepository.getById(1));
        assertThat(exception).hasMessageContaining("No book with id = 1 was found");
    }

    @Test
    public void shouldUpdateBook() {
        Book expectedBook = new Book(1, "Something strange", new Author(1, "Stanislav Herman Lem"),
                new Genre(1, "fantasy"));

        boolean updated = bookRepository.update(expectedBook);

        assertThat(updated).isTrue();
        Book updatedBook = bookRepository.getById(1);
        assertThat(updatedBook.getTitle()).isEqualTo("Something strange");
    }

    @Test
    public void shouldThrowExceptionWhenThereIsNoBookWithCurrentId() {
        NoBookFoundException exception = assertThrows(NoBookFoundException.class, () -> bookRepository.getById(Integer.MAX_VALUE));

        assertThat(exception).hasMessageContaining("No book with id = " + Integer.MAX_VALUE + " was found");
    }
}