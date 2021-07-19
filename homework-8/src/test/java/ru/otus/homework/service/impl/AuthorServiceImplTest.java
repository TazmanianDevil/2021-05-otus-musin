package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.AuthorRepository;
import ru.otus.homework.model.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorServiceImplTest {

    private static final String ID = "ID";
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnBook() {
        when(authorRepository.findById(anyString())).thenReturn(Optional.of(mock(Author.class)));

        final Optional<Author> authorOptional = authorService.findById(ID);

        assertThat(authorOptional).isNotEmpty();
    }

    @Test
    public void shouldReturnEmptyOptionalWhenBookIsNotFound() {
        when(authorRepository.findById(anyString())).thenReturn(Optional.empty());

        final Optional<Author> authorOptional = authorService.findById(ID);

        assertThat(authorOptional).isEmpty();
    }
}