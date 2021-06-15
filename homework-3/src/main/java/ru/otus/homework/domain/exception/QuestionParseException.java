package ru.otus.homework.domain.exception;

public class QuestionParseException extends RuntimeException {
    public QuestionParseException() {
        super("Can't parse question");
    }

    public QuestionParseException(String message) {
        super(message);
    }
}
