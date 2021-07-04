package ru.otus.homework.service.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.impl.JdbcBookRepository;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    private static final long ID = 1;
    private static final String TITLE = "TITLE";
    private static final String AUTHOR_NAME = "AUTHOR_NAME";
    private static final String GENRE_NAME = "GENRE_NAME";
    @Mock
    private JdbcBookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetById() {
        final Book expectedBook = new Book(ID, TITLE, new Author(ID, AUTHOR_NAME), new Genre(ID, GENRE_NAME));
        when(bookRepository.getById(eq(ID))).thenReturn(expectedBook);

        final Book book = bookService.getById(ID);

        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void shouldDeleteById() {
        when(bookRepository.deleteById(eq(ID))).thenReturn(true);

        boolean deleted = bookService.deleteById(ID);

        assertTrue(deleted);
    }

    @Test
    public void shouldGetAll() {
        List<Book> expectedBooks = new EasyRandom()
                .objects(Book.class, 2)
                .collect(Collectors.toList());
        when(bookRepository.getAll()).thenReturn(expectedBooks);

        List<Book> books = bookService.getAll();

        assertThat(books).hasSameElementsAs(expectedBooks);
    }

    @Test
    public void shouldInsertBook() {
        final Book expectedBook = new Book(0, TITLE, new Author(ID, AUTHOR_NAME), new Genre(ID, GENRE_NAME));
        when(bookRepository.insert(any(Book.class))).thenReturn(ID);

        long id = bookService.insert(expectedBook);

        assertThat(id).isEqualTo(ID);
    }

    @Test
    public void shouldUpdateBook() {
        final Book expectedBook = new Book(ID, TITLE, new Author(ID, AUTHOR_NAME), new Genre(ID, GENRE_NAME));
        when(bookRepository.update(eq(expectedBook))).thenReturn(true);

        boolean updated = bookService.update(expectedBook);

        assertTrue(updated);
    }
}