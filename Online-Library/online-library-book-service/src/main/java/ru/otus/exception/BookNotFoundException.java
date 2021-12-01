package ru.otus.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(long id) {
        super(String.format("Book with id=%d must exist", id));
    }
}
