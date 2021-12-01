package ru.otus.exception;

public class WrongAuthorException extends RuntimeException {
    public WrongAuthorException() {
        super("Author must exist");
    }
}