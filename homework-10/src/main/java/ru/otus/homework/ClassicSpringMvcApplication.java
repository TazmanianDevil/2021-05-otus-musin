package ru.otus.homework;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class ClassicSpringMvcApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(ClassicSpringMvcApplication.class, args);
    }

}
