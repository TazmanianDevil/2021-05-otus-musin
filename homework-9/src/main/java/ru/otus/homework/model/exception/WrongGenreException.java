package ru.otus.homework.model.exception;

public class WrongGenreException extends LibraryException {
    public WrongGenreException() {
        super("Genre must exist");
    }
}
