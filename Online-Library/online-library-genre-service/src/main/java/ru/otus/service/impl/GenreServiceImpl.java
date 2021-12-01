package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.GenreRepository;
import ru.otus.model.Genre;
import ru.otus.model.GenreNotFoundException;
import ru.otus.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(GenreNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public Genre create(String genreName) {
        return genreRepository.save(new Genre(genreName));
    }
}
