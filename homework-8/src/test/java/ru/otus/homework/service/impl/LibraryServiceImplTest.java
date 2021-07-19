package ru.otus.homework.service.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.Genre;
import ru.otus.homework.model.exception.AuthorNotFoundException;
import ru.otus.homework.model.exception.BookNotFoundException;
import ru.otus.homework.model.exception.CommentNotFoundException;
import ru.otus.homework.model.exception.GenreNotFoundException;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.CommentService;
import ru.otus.homework.service.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class LibraryServiceImplTest {

    private static final String BOOK_ID = "BOOK_ID";
    private static final String COMMENT_ID = "COMMENT_ID";
    private static final String COMMENT_TEXT = "COMMENT_TEXT";
    private static final String AUTHOR_ID = "AUTHOR_ID";
    private static final String GENRE_ID = "GENRE_ID";
    private static final String BOOK_TITLE = "BOOK_TITLE";
    @Mock
    private BookService bookService;
    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;
    @Mock
    private CommentService commentService;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenBookHasNotFound() {
        when(bookService.findById(eq(BOOK_ID))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> libraryService.findBookById(BOOK_ID)).isInstanceOf(BookNotFoundException.class)
                .hasMessage("Book not found in Library");
        verify(bookService, times(1)).findById(eq(BOOK_ID));
    }

    @Test
    public void shouldFindBookById() {
        when(bookService.findById(eq(BOOK_ID))).thenReturn(Optional.of(mock(Book.class)));

        final Book book = libraryService.findBookById(BOOK_ID);
        assertThat(book).isNotNull();
    }

    @Test
    public void shouldFindAllBook() {
        when(bookService.findAllBooks()).thenReturn(new EasyRandom().objects(Book.class, 2)
                .collect(Collectors.toList()));

        final List<Book> books = libraryService.findAllBooks();

        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    public void shouldThrowAuthorNotFoundExceptionWhenAuthorIsNotFound() {
        when(authorService.findById(anyString())).thenReturn(Optional.empty());
        final Book book = new Book(null, BOOK_TITLE, new Author(AUTHOR_ID), new Genre(GENRE_ID));

        assertThatThrownBy(() -> libraryService.saveBook(book))
                .isInstanceOf(AuthorNotFoundException.class)
                .hasMessage("Author not found in Library");
        verify(authorService, times(1)).findById(anyString());
        verify(genreService, times(0)).findById(anyString());
    }

    @Test
    public void shouldThrowGenreNotFoundExceptionWhenGenreIsNotFound() {
        when(authorService.findById(anyString())).thenReturn(Optional.of(mock(Author.class)));
        when(genreService.findById(anyString())).thenReturn(Optional.empty());

        final Book book = new Book(null, BOOK_TITLE, new Author(AUTHOR_ID), new Genre(GENRE_ID));
        assertThatThrownBy(() -> {
            libraryService.saveBook(book);
        })
                .isInstanceOf(GenreNotFoundException.class)
                .hasMessage("Genre not found in Library");
        verify(authorService, times(1)).findById(eq(book.getAuthor().getId()));
        verify(authorService, times(0)).findById(eq(book.getGenre().getId()));
        verify(bookService, times(0)).save(eq(book));
    }

    @Test
    public void shouldDeleteBookById() {
        doNothing().when(bookService).deleteById(eq(BOOK_ID));

        libraryService.deleteBookById(BOOK_ID);

        verify(bookService, times(1)).deleteById(eq(BOOK_ID));
    }

    @Test
    public void shouldThrowExceptionWhenBookIsNotFound() {
        when(bookService.findById(anyString())).thenReturn(Optional.empty());
        Comment comment = new Comment(null, COMMENT_TEXT, BOOK_ID);

        assertThatThrownBy(() -> libraryService.saveComment(comment))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("Book not found in Library");
        verify(bookService, times(1)).findById(anyString());
        verify(commentService, times(0)).save(eq(comment));
    }

    @Test
    public void shouldSaveComment() {
        when(bookService.findById(eq(BOOK_ID))).thenReturn(Optional.of(mock(Book.class)));
        Comment comment = new Comment(null, COMMENT_TEXT, BOOK_ID);
        when(commentService.save(any(Comment.class))).thenReturn(comment);

        final Comment savedComment = libraryService.saveComment(comment);
        assertThat(savedComment).isNotNull()
                .usingRecursiveComparison().isEqualTo(comment);
        verify(bookService, times(1)).findById(comment.getBookId());
        verify(commentService, times(1)).save(eq(comment));
    }

    @Test
    public void shouldThrowExceptionWhenCommentHasNotFound() {
        when(commentService.findById(eq(COMMENT_ID))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> libraryService.findCommentById(COMMENT_ID)).isInstanceOf(CommentNotFoundException.class)
                .hasMessage("Comment not found");
        verify(commentService, times(1)).findById(eq(COMMENT_ID));
    }

    @Test
    public void shouldFindCommentById() {
        when(commentService.findById(eq(COMMENT_ID))).thenReturn(Optional.of(mock(Comment.class)));

        final Comment comment = libraryService.findCommentById(COMMENT_ID);
        assertThat(comment).isNotNull();
    }

    @Test
    public void shouldFindAllCommentsByBookId() {
        when(commentService.findAllByBookId(eq(BOOK_ID))).thenReturn(new EasyRandom().objects(Comment.class, 2)
                .collect(Collectors.toList()));

        final List<Comment> comments = libraryService.findCommentsByBookId(BOOK_ID);

        assertThat(comments).hasSize(2);
    }

    @Test
    public void shouldDeleteCommentById() {
        doNothing().when(commentService).deleteById(eq(COMMENT_ID));

        libraryService.deleteCommentById(COMMENT_ID);

        verify(commentService, times(1)).deleteById(eq(COMMENT_ID));
    }

}