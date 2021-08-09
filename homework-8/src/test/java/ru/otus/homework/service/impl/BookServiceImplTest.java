package ru.otus.homework.service.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.BookRepository;
import ru.otus.homework.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    private static final String ID = "ID";
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnBook() {
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(mock(Book.class)));

        final Optional<Book> bookOptional = bookService.findById(ID);

        assertThat(bookOptional).isNotEmpty();
        verify(bookRepository, times(1)).findById(eq(ID));
    }

    @Test
    public void shouldReturnEmptyOptionalWhenBookIsNotFound() {
        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());

        final Optional<Book> bookOptional = bookService.findById(ID);

        assertThat(bookOptional).isEmpty();
        verify(bookRepository, times(1)).findById(eq(ID));
    }

    @Test
    public void shouldSaveBook() {
        final Book bookMock = mock(Book.class);
        when(bookRepository.save(any(Book.class))).thenReturn(bookMock);

        final Book book = new Book(ID);
        final Book savedBook = bookService.save(book);

        assertThat(savedBook).isNotNull()
                .usingRecursiveComparison().isEqualTo(bookMock);
        verify(bookRepository, times(1)).save(eq(book));
    }

    @Test
    public void shouldFindAllBooks() {
        when(bookRepository.findAll())
                .thenReturn(new EasyRandom().objects(Book.class, 2)
                        .collect(Collectors.toList()));

        final List<Book> books = bookService.findAllBooks();
        assertThat(books).isNotEmpty().hasSize(2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void shouldDeleteBookById() {
        doNothing().when(bookRepository).deleteById(eq(ID));

        bookService.deleteById(ID);

        verify(bookRepository, times(1)).deleteById(eq(ID));
    }
}