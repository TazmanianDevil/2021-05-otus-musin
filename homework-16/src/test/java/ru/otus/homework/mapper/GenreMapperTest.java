package ru.otus.homework.mapper;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.model.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GenreMapperTest {
    @Test
    public void shouldReturnEmptyListWhenGenresListIsNullOrEmpty() {
        assertThat(new GenreMapper().toDto((List) null)).isEmpty();
        assertThat(new GenreMapper().toDto(Collections.emptyList())).isEmpty();
    }

    @Test
    public void shouldMapList() {
        final Genre genre = new EasyRandom().nextObject(Genre.class);
        final List<GenreDto> dtoList = new GenreMapper().toDto(Collections.singletonList(genre));
        assertThat(dtoList).hasSize(1);
    }

    @Test
    public void shouldMapBook() {
        final Genre genre = new EasyRandom().nextObject(Genre.class);
        final GenreDto genreDto = new GenreMapper().toDto(genre);
        assertThat(genreDto).isNotNull();
        assertThat(genreDto.getId()).isEqualTo(genre.getId());
        assertThat(genreDto.getName()).isEqualTo(genre.getName());
    }
}