package ru.otus.homework.model.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book not found in Library");
    }
}
