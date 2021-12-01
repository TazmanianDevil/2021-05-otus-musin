package ru.otus.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(long id) {
        super(String.format("Author with id = %d must exist", id));
    }
}