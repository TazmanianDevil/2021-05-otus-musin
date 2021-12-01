package ru.otus.model;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(long id) {
        super(String.format("Genre with id=%d must exist", id));
    }
}
