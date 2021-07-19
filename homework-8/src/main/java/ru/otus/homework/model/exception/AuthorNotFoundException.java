package ru.otus.homework.model.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Author not found in Library");
    }
}
