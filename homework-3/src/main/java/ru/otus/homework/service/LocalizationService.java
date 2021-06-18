package ru.otus.homework.service;

public interface LocalizationService {

    String getLocalizedMessage(String source);

    String getLocalizedMessage(String source, Object[] parameters);
}
