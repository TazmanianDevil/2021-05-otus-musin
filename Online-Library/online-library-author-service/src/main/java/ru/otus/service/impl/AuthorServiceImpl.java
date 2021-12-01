package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorRepository;
import ru.otus.model.Author;
import ru.otus.model.AuthorNotFoundException;
import ru.otus.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional (readOnly = true)
    @Override
    public Author getById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(AuthorNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getByFullName(String fullName) {
        return Optional.ofNullable(authorRepository.findByFullName(fullName));
    }

    @Transactional
    @Override
    public Author create(String fullName) {
        return authorRepository.save(new Author(fullName));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
