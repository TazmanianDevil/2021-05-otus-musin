package ru.otus.homework.conroller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.homework.dao.GenreRepository;
import ru.otus.homework.model.Genre;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
public class GenreController {

    @Bean
    public RouterFunction<ServerResponse> composedGenreRouter(GenreRepository repository) {
        return route()
                .GET("/api/genres", RequestPredicates.accept(MediaType.APPLICATION_JSON),
                        request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(repository.findAll(), Genre.class))
                .build();
    }
}
