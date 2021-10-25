package ru.otus.homework.service.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.AuthorRepository;
import ru.otus.homework.model.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {
    private static final long ID = 42;
    private static final String FULL_NAME = "FULL_NAME";
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetById() {
        when(authorRepository.findById(ID)).thenReturn(Optional.of(new Author()));

        final Optional<Author> authorOptional = authorService.getById(42);

        assertThat(authorOptional).isNotEmpty();
        verify(authorRepository, times(1)).findById(ID);
    }

    @Test
    public void shouldReturnEmptyOptionalWhenAuthorIsAbsent() {
        when(authorRepository.findByFullName(anyString())).thenReturn(null);

        final Optional<Author> authorOptional = authorService.getByFullName(FULL_NAME);

        assertThat(authorOptional).isEmpty();
        verify(authorRepository, times(1)).findByFullName(FULL_NAME);
    }

    @Test
    public void shouldReturnNotEmptyOptionalWhenAuthorIsAbsent() {
        final Author author = new EasyRandom().nextObject(Author.class);

        when(authorRepository.findByFullName(FULL_NAME)).thenReturn(author);

        final Optional<Author> authorOptional = authorService.getByFullName(FULL_NAME);

        assertThat(authorOptional).isNotEmpty();
        verify(authorRepository, times(1)).findByFullName(FULL_NAME);
        final Author actualAuthor = authorOptional.get();
        assertThat(actualAuthor).usingRecursiveComparison()
                .isEqualTo(author);
    }

    @Test
    public void shouldCreateAuthor() {
        when(authorRepository.save(any())).thenReturn(new Author());

        final Author author = authorService.create(FULL_NAME);

        assertThat(author).isNotNull();
        final ArgumentCaptor<Author> argumentCaptor = ArgumentCaptor.forClass(Author.class);
        verify(authorRepository, times(1)).save(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getFullName()).isEqualTo(FULL_NAME);
    }
}