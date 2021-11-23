package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer
public class LibraryDiscoverServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryDiscoverServiceApplication.class, args);
	}

}
