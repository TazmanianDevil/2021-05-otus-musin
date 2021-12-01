package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class LibraryHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryHystrixDashboardApplication.class, args);
	}

}
