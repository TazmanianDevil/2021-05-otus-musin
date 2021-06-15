package ru.otus.homework.service;

import ru.otus.homework.domain.TestingResult;

public interface OutputService {
    void printResults(String fullName, TestingResult result);
}
