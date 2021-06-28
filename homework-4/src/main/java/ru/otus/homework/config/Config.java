package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class Config {

    @Bean
    public ClassPathResource resourceFile(TestingSystemConfiguration configuration) {
        return new ClassPathResource(configuration.getFilePath() + "_" + configuration.getLocale() + "." + configuration.getFileExtension());
    }
}

