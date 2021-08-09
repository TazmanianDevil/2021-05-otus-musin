package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.GenreRepository;
import ru.otus.homework.model.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GenreServiceImplTest {

    private static final String ID = "ID";
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnBook() {
        when(genreRepository.findById(anyString())).thenReturn(Optional.of(mock(Genre.class)));

        final Optional<Genre> genreOptional = genreService.findById(ID);

        assertThat(genreOptional).isNotEmpty();
    }

    @Test
    public void shouldReturnEmptyOptionalWhenBookIsNotFound() {
        when(genreRepository.findById(anyString())).thenReturn(Optional.empty());

        final Optional<Genre> genreOptional = genreService.findById(ID);

        assertThat(genreOptional).isEmpty();
    }
}