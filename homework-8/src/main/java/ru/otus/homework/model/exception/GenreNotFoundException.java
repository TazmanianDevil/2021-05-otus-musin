package ru.otus.homework.model.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException() {
        super("Genre not found in Library");
    }
}
