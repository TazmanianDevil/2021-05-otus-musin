package ru.otus.homework.model.exception;

public class WrongGenreException extends RuntimeException{

    public WrongGenreException() {
        super("Genre must exist in library");
    }
}
