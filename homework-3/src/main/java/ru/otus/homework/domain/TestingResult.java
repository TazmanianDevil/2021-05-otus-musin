package ru.otus.homework.domain;

import lombok.Value;

@Value
public class TestingResult {
    int totalQuestions;
    int correctAnswers;
    int userCorrectAnswers;

    public boolean isSuccessTesting() {
        return correctAnswers <= userCorrectAnswers;
    }
}
