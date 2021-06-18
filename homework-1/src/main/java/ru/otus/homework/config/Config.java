package ru.otus.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Bean
    public ClassPathResource resourceFile(@Value("${filePath}") String filePath) {
        return new ClassPathResource(filePath);
    }
}
