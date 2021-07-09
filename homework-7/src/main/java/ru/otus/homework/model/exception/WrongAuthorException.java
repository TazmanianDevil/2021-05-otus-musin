package ru.otus.homework.model.exception;

public class WrongAuthorException extends RuntimeException {
    public WrongAuthorException() {
        super("Author must exist");
    }
}
