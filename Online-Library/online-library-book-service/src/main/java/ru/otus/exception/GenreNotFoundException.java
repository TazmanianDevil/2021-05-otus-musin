package ru.otus.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException() {
        super("Genre is absent in library!");
    }
}
