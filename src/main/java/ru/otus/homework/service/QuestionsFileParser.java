package ru.otus.homework.service;

import ru.otus.homework.domain.Question;

import java.util.Collection;

public interface QuestionsFileParser {
    Collection<Question> parseQuestions();
}
