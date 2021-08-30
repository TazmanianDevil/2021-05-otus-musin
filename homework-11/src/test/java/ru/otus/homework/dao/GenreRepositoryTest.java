package ru.otus.homework.dao;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.otus.homework.model.Genre;

@DataMongoTest
@EnableMongock
class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Test
    public void shouldGetAllGenres() {
        final Flux<Genre> genreFlux = repository.findAll();
        StepVerifier.create(genreFlux)
                .expectNextMatches(g -> g.getName().equals("novel"))
                .expectComplete()
                .verify();
    }
}