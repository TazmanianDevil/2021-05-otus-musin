package ru.otus.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Author is absent in library!");
    }
}
