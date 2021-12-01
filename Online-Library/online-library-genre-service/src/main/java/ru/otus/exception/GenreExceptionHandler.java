package ru.otus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.model.GenreError;
import ru.otus.model.GenreNotFoundException;

@ControllerAdvice
public class GenreExceptionHandler {

    @ExceptionHandler(GenreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GenreError handleGenreNotFoundException(GenreNotFoundException e) {
        return new GenreError(e.getMessage());
    }
}
