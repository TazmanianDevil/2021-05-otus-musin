package ru.otus.model;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Author must exist");
    }
}