package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dao.GenreRepository;
import ru.otus.homework.model.Genre;
import ru.otus.homework.service.GenreService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public Optional<Genre> getById(long id) {
        return genreRepository.findById(id);
    }
}
