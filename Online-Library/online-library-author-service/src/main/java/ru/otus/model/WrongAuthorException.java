package ru.otus.model;

public class WrongAuthorException extends RuntimeException {
    public WrongAuthorException() {
        super("Author must exist");
    }
}