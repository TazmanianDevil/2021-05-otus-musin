package ru.otus.homework.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.impl.JpaBookRepository;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;
import ru.otus.homework.service.impl.BookServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    private static final long ID = 1;
    private static final String TITLE = "TITLE";
    private static final String AUTHOR_NAME = "AUTHOR_NAME";
    private static final String GENRE_NAME = "GENRE_NAME";

    @Mock
    private JpaBookRepository bookRepository;

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
        doNothing().when(bookRepository).deleteById(eq(ID));

        bookService.deleteById(ID);

        verify(bookRepository, times(1)).deleteById(ID);
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
    public void shouldCreateBook() {
        final Book book = new Book(0, TITLE, mock(Author.class), mock(Genre.class));
        when(bookRepository.create(book)).thenReturn(book);

        bookService.create(book);
        verify(bookRepository, times(1)).create(book);
    }

    @Test
    public void shouldUpdateBook() {
        final Book expectedBook = new Book(ID, TITLE, new Author(ID, AUTHOR_NAME), new Genre(ID, GENRE_NAME));
        when(bookRepository.update(eq(expectedBook))).thenReturn(expectedBook);

        Book book = bookService.update(expectedBook);

        verify(bookRepository, times(1)).update(book);
    }
}