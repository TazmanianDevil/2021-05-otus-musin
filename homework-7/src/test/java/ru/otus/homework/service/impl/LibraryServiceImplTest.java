package ru.otus.homework.impl;

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
import ru.otus.homework.model.exception.WrongBookException;
import ru.otus.homework.service.impl.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LibraryServiceImplTest {

    private static final long COMMENT_ID = 42;
    private static final long BOOK_ID = 42;
    private static final String TEXT = "TEXT";
    @Mock
    private CommentServiceImpl commentService;

    @Mock
    private BookServiceImpl bookService;

    @Mock
    private AuthorServiceImpl authorService;

    @Mock
    private GenreServiceImpl genreService;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenCreateCommentAndBookIsNotInLibrary() {
        when(bookService.getById(eq(BOOK_ID))).thenReturn(Optional.empty());
        assertThatThrownBy(() -> libraryService.createComment(new Comment(0, TEXT, new Book(BOOK_ID))))
                .hasMessageContaining("Book must exist in library")
                .isInstanceOf(WrongBookException.class);
    }

    @Test
    public void shouldCreateComment() {
        when(bookService.getById(eq(BOOK_ID))).thenReturn(Optional.of(mock(Book.class)));
        final Comment comment = new Comment(COMMENT_ID, TEXT, new Book(BOOK_ID));
        when(commentService.create(any(Comment.class))).thenReturn(comment);

        final Comment actualComment = libraryService.createComment(new Comment(0, TEXT, new Book(BOOK_ID)));

        assertThat(actualComment).isNotNull()
                .matches(c -> comment.getId() == COMMENT_ID)
                .matches(c -> c.getText().equals(TEXT));

        verify(bookService, times(1)).getById(eq(BOOK_ID));
        verify(commentService, times(1)).create(any(Comment.class));
    }

    @Test
    public void shouldGetBookById() {
        when(bookService.getById(BOOK_ID)).thenReturn(Optional.of(mock(Book.class)));

        final Book book = libraryService.getBookById(BOOK_ID);

        assertThat(book).isNotNull();
        verify(bookService, times(1)).getById(eq(BOOK_ID));
    }

    @Test
    public void shouldGetAllBooks() {
        when(bookService.getAll()).thenReturn(new EasyRandom()
                .objects(Book.class, 2)
                .collect(Collectors.toList()));

        final List<Book> books = libraryService.getAllBooks();

        assertThat(books).isNotEmpty();
        assertThat(books).hasSize(2);
        verify(bookService, times(1)).getAll();
    }

    @Test
    public void shouldCreateBook() {
        when(bookService.create(any(Book.class))).thenReturn(mock(Book.class));
        when(authorService.getById(anyLong())).thenReturn(Optional.of(mock(Author.class)));
        when(genreService.getById(anyLong())).thenReturn(Optional.of(mock(Genre.class)));
        final Book book = libraryService.createBook(new Book(0, TEXT, mock(Author.class), mock(Genre.class)));

        assertThat(book).isNotNull();
        verify(bookService, times(1)).create(any(Book.class));
    }

    @Test
    public void shouldDeleteBook() {
        doNothing().when(bookService).deleteById(eq(BOOK_ID));

        libraryService.deleteBookById(BOOK_ID);

        verify(bookService, times(1)).deleteById(eq(BOOK_ID));
    }

    @Test
    public void shouldUpdateBook() {
        when(bookService.update(any(Book.class))).thenReturn(mock(Book.class));

        final Book book = libraryService.updateBook(new Book(BOOK_ID, TEXT, mock(Author.class), mock(Genre.class)));

        assertThat(book).isNotNull();
        verify(bookService, times(1)).update(any(Book.class));
    }

    @Test
    public void shouldGetCommentById() {
        final Comment expectedComment = mock(Comment.class);
        when(commentService.getById(COMMENT_ID)).thenReturn(Optional.of(expectedComment));

        final Comment comment = libraryService.getCommentById(BOOK_ID);

        assertThat(comment).isNotNull();
        assertThat(comment).usingRecursiveComparison()
                .isEqualTo(expectedComment);
        verify(commentService, times(1)).getById(eq(COMMENT_ID));
    }

    @Test
    public void shouldGetAllComments() {
        when(commentService.getAllByBookId(eq(BOOK_ID))).thenReturn(new EasyRandom()
                .objects(Comment.class, 2)
                .collect(Collectors.toList()));

        final List<Comment> coments = libraryService.getAllCommentsByBookId(BOOK_ID);

        assertThat(coments).isNotEmpty();
        assertThat(coments).hasSize(2);
        verify(commentService, times(1)).getAllByBookId(eq(BOOK_ID));
    }


    @Test
    public void shouldDeleteComment() {
        doNothing().when(commentService).deleteById(eq(COMMENT_ID));

        libraryService.deleteCommentById(COMMENT_ID);

        verify(commentService, times(1)).deleteById(eq(COMMENT_ID));
    }

    @Test
    public void shouldUpdateComment() {
        when(commentService.update(any(Comment.class))).thenReturn(mock(Comment.class));

        final Comment comment = libraryService.updateComment(new Comment(0, TEXT, mock(Book.class)));

        assertThat(comment).isNotNull();
        verify(commentService, times(1)).update(any(Comment.class));
    }
}