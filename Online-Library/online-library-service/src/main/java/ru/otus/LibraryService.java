package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@EnableEurekaClient
public class LibraryService {

    public static void main(String[] args) {
        SpringApplication.run(LibraryService.class, args);
    }

}
