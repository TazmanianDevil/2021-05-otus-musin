package ru.otus.homework.model.exception;

public class WrongBookException extends LibraryException {
    public WrongBookException() {
        super("Book must exist in library");
    }
}
