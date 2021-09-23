package ru.otus.homework.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Component
public class GenreMapper {
    public GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public List<GenreDto> toDto(List<Genre> genres) {
        return emptyIfNull(genres).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
