package ru.otus.homework.model.exception;

public class WrongBookException extends RuntimeException {
    public WrongBookException() {
        super("Book must exist in library");
    }
}
