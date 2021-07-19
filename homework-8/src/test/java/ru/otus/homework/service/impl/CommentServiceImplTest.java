package ru.otus.homework.service.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.CommentRepository;
import ru.otus.homework.model.Comment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    private static final String ID = "ID";
    private static final String TEXT = "TEXT";
    private static final String BOOK_ID = "BOOK_ID";
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnComment() {
        when(commentRepository.findById(anyString())).thenReturn(Optional.of(mock(Comment.class)));

        final Optional<Comment> commentOptional = commentService.findById(ID);

        assertThat(commentOptional).isNotEmpty();
        verify(commentRepository, times(1)).findById(eq(ID));
    }

    @Test
    public void shouldReturnEmptyOptionalWhenCommentIsNotFound() {
        when(commentRepository.findById(anyString())).thenReturn(Optional.empty());

        final Optional<Comment> commentOptional = commentService.findById(ID);

        assertThat(commentOptional).isEmpty();
        verify(commentRepository, times(1)).findById(eq(ID));
    }

    @Test
    public void shouldSaveComment() {
        final Comment commentMock = mock(Comment.class);
        when(commentRepository.save(any(Comment.class))).thenReturn(commentMock);

        final Comment comment = new Comment(ID, TEXT, BOOK_ID);
        final Comment savedComment = commentService.save(comment);

        assertThat(savedComment).isNotNull()
                .usingRecursiveComparison().isEqualTo(commentMock);
        verify(commentRepository, times(1)).save(eq(comment));
    }

    @Test
    public void shouldFindAllComments() {
        when(commentRepository.findAllByBookId(eq(BOOK_ID)))
                .thenReturn(new EasyRandom().objects(Comment.class, 2)
                        .collect(Collectors.toList()));

        final List<Comment> comments = commentService.findAllByBookId(BOOK_ID);
        assertThat(comments).isNotEmpty().hasSize(2);
        verify(commentRepository, times(1)).findAllByBookId(eq(BOOK_ID));
    }

    @Test
    public void shouldDeleteCommentById() {
        doNothing().when(commentRepository).deleteById(eq(ID));

        commentService.deleteById(ID);

        verify(commentRepository, times(1)).deleteById(eq(ID));
    }
}