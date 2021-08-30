package ru.otus.homework.conroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    @Qualifier("composedBookRoutes")
    private RouterFunction<ServerResponse> route;

    @Test
    public void testGetAllRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .hasSize(1);
    }

    @Test
    public void testGetByIdRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/api/books/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Book.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody()).isNotNull();
                    assertThat(response.getResponseBody().getId()).isEqualTo("1");
                    assertThat(response.getResponseBody().getTitle()).isEqualTo("War and peace");
                });
    }

    @Test
    public void testSaveRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        Book book = new Book("title", new Author("fullName"), new Genre("genre"));

        client.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(book))
                .exchange()
                .expectStatus()
                .isOk();
    }
}