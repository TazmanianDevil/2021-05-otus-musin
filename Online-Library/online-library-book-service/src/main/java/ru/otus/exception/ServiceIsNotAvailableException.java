package ru.otus.exception;

public class ServiceIsNotAvailableException extends ApiException {
    public ServiceIsNotAvailableException(String s) {
        super(String.format("Service %s is not available", s));
    }
}
