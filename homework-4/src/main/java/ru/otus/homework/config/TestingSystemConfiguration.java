package ru.otus.homework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "testing")
@Component
@Data
public class TestingSystemConfiguration {
    private String filePath;
    private String fileExtension;
    private Answers answers;
    private String locale;

    @Data
    public static class Answers {
        private int total;
        private int correct;
    }
}
