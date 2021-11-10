package ru.otus.homework.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.dto.SaveBookRequest;
import ru.otus.homework.mapper.BookMapper;
import ru.otus.homework.mapper.GenreMapper;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;
import ru.otus.homework.model.exception.WrongBookException;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.impl.LibraryServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class LibraryServiceImplTest {
    private static final long BOOK_ID = 42;
    private static final long AUTHOR_ID = 13;
    private static final long GENRE_ID = 21;
    private static final String TEXT = "TEXT";
    private static final String FULL_NAME = "FULL_NAME";
    private static final String GENRE_NAME = "GENRE_NAME";
    @Mock
    private BookService bookService;
    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private BookMapper bookMapper;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private GenreMapper genreMapper;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetBookById() {
        when(bookService.getById(BOOK_ID)).thenReturn(Optional.of(mock(Book.class)));

        final BookDto book = libraryService.getBookById(BOOK_ID);

        assertThat(book).isNotNull();
        verify(bookService, times(1)).getById(eq(BOOK_ID));
    }

    @Test
    public void shouldThrowExceptionWhenBookIsAbsent() {
        when(bookService.getById(BOOK_ID)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> libraryService.getBookById(BOOK_ID))
                .isInstanceOf(WrongBookException.class);
    }

    @Test
    public void shouldGetAllBooks() {
        when(bookService.getAll()).thenReturn(new EasyRandom()
                .objects(Book.class, 2)
                .collect(Collectors.toList()));

        final List<BookDto> books = libraryService.getAllBooks();

        assertThat(books).isNotEmpty();
        assertThat(books).hasSize(2);
        verify(bookService, times(1)).getAll();
    }

    @Test
    public void shouldGetAllGenres() {
        when(genreService.getAll()).thenReturn(new EasyRandom()
                .objects(Genre.class, 2)
                .collect(Collectors.toList()));

        final List<GenreDto> books = libraryService.getAllGenres();

        assertThat(books).isNotEmpty();
        assertThat(books).hasSize(2);
        verify(genreService, times(1)).getAll();
    }

    @Test
    public void shouldDeleteBook() {
        doNothing().when(bookService).deleteById(eq(BOOK_ID));

        libraryService.deleteBookById(BOOK_ID);

        verify(bookService, times(1)).deleteById(eq(BOOK_ID));
    }

    @Test
    public void shouldCreateAuthorWhenCreatingBook() {
        when(authorService.getByFullName(FULL_NAME)).thenReturn(Optional.empty());
        when(authorService.create(FULL_NAME)).thenReturn(new Author(AUTHOR_ID, FULL_NAME));
        when(genreService.getById(anyLong())).thenReturn(Optional.of(new Genre(GENRE_ID, GENRE_NAME)));

        when(bookService.create(any(Book.class))).thenReturn(mock(Book.class));

        final BookDto bookDto = libraryService.createBook(new SaveBookRequest(0, TEXT, FULL_NAME, GENRE_ID));

        final ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
        assertThat(bookDto).isNotNull();
        verify(authorService, times(1)).getByFullName(FULL_NAME);
        verify(authorService, times(1)).create(FULL_NAME);
        verify(bookService, times(1)).create(bookCaptor.capture());

        assertThat(bookCaptor).isNotNull();
        final Book book = bookCaptor.getValue();
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(TEXT);
        assertThat(book.getAuthor().getFullName()).isEqualTo(FULL_NAME);
        assertThat(book.getGenre().getId()).isEqualTo(GENRE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenBookIsAbsentWhenUpdatingBook() {
        when(bookService.getById(BOOK_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> libraryService.updateBook(new SaveBookRequest(BOOK_ID, TEXT, FULL_NAME, GENRE_ID)))
                .isInstanceOf(WrongBookException.class);
    }


    @Test
    public void shouldCreateAuthorWhenUpdatingBook() {
        when(bookService.getById(BOOK_ID)).thenReturn(Optional.of(new Book(BOOK_ID)));

        when(authorService.getByFullName(FULL_NAME)).thenReturn(Optional.empty());
        when(authorService.create(FULL_NAME)).thenReturn(new Author(AUTHOR_ID, FULL_NAME));
        when(genreService.getById(anyLong())).thenReturn(Optional.of(new Genre(GENRE_ID, GENRE_NAME)));

        when(bookService.update(any(Book.class))).thenReturn(mock(Book.class));

        final BookDto bookDto = libraryService.updateBook(new SaveBookRequest(BOOK_ID, TEXT, FULL_NAME, GENRE_ID));

        final ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
        assertThat(bookDto).isNotNull();
        verify(authorService, times(1)).getByFullName(FULL_NAME);
        verify(authorService, times(1)).create(FULL_NAME);
        verify(bookService, times(1)).update(bookCaptor.capture());

        assertThat(bookCaptor).isNotNull();
        final Book book = bookCaptor.getValue();
        assertThat(book).isNotNull();
        assertThat(book.getId()).isEqualTo(BOOK_ID);
        assertThat(book.getTitle()).isEqualTo(TEXT);
        assertThat(book.getAuthor().getFullName()).isEqualTo(FULL_NAME);
        assertThat(book.getGenre().getId()).isEqualTo(GENRE_ID);
    }
}
