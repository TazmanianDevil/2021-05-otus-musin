package ru.otus.homework.service;

import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.TestingResult;

import java.util.Collection;

public interface TestingEngine {
    TestingResult test(Collection<Question> questions);
}
