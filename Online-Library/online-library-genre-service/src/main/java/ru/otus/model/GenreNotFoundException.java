package ru.otus.model;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException() {
        super("Genre must exist");
    }
}
