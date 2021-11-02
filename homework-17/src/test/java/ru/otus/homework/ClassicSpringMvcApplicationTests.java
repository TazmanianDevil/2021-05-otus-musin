package ru.otus.homework;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.otus.homework.util.CustomPostgreSQLContainer;

@SpringBootTest
@Testcontainers
class ClassicSpringMvcApplicationTests {

    @Container
    private static final PostgreSQLContainer<?> container = CustomPostgreSQLContainer.getInstance();

    @Test
    void contextLoads() {
    }

}
