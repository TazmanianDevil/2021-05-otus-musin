package ru.otus.homework.model.exception;

public class WrongCommentException extends RuntimeException {

    public WrongCommentException() {
        super("Comment must exist");
    }
}
