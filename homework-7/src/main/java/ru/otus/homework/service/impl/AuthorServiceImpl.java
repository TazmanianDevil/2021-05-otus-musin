package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dao.AuthorRepository;
import ru.otus.homework.model.Author;
import ru.otus.homework.service.AuthorService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Optional<Author> getById(long id) {
        return authorRepository.findById(id);
    }
}
