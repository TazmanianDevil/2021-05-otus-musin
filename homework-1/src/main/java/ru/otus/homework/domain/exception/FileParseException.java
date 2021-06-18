package ru.otus.homework.domain.exception;

public class FileParseException extends RuntimeException {
    public FileParseException() {
        super("Cant't read/parse questions file!");
    }

    public FileParseException(String message) {
        super(message);
    }
}
