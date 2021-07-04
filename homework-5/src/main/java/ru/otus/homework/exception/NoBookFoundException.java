package ru.otus.homework.exception;

public class NoBookFoundException extends RuntimeException {

    public NoBookFoundException(long id, Exception e) {
        super(String.format("No book with id = %s was found", id), e);
    }
}
