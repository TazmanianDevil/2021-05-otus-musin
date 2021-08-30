package ru.otus.homework.conroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.homework.model.Genre;

@SpringBootTest
public class GenreControllerTest {

    @Autowired
    @Qualifier("composedGenreRouter")
    private RouterFunction<ServerResponse> route;

    @Test
    public void testGetAllRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/api/genres")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Genre.class)
                .hasSize(1)
                .contains(new Genre("novel"));

    }
}