package ru.otus.homework.model.exception;

public class WrongCommentException extends LibraryException {

    public WrongCommentException() {
        super("Comment must exist");
    }
}
