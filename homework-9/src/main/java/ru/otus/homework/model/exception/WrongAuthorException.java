package ru.otus.homework.model.exception;

public class WrongAuthorException extends LibraryException {
    public WrongAuthorException() {
        super("Author must exist");
    }
}